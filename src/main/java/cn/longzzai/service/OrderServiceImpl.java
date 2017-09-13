package cn.longzzai.service;

import cn.longzzai.dataobject.OrderDetail;
import cn.longzzai.dataobject.OrderMaster;
import cn.longzzai.dataobject.ProductInfo;
import cn.longzzai.dto.CartDTO;
import cn.longzzai.dto.OrderDTO;
import cn.longzzai.enums.OrderStatusEnum;
import cn.longzzai.enums.PayStatusEnum;
import cn.longzzai.enums.ResultEnum;
import cn.longzzai.exception.SellException;
import cn.longzzai.repository.OrderDetailRepository;
import cn.longzzai.repository.OrderMasterRepository;
import cn.longzzai.utils.KeyUtil;
import cn.longzzai.utils.converter.OrderMaster2OrderDTOConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * order服务层
 *
 * @author longcho
 * 2017-08-30-17:45
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetailRepository detailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        //查询商品（数量，价格）
        BigDecimal orderAmount = new BigDecimal(0);
        //设置订单id
        String orderId =  KeyUtil.getEasyKey();
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算总价
            orderAmount = productInfo.getProductPrice().
                    multiply(new BigDecimal(orderDetail.getProductQuantity())).
                    add(orderAmount);
            //订单详情入库
            BeanUtils.copyProperties(productInfo , orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.getEasyKey());
            detailRepository.save(orderDetail);
        }
        //写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO , orderMaster);
        orderMasterRepository.save(orderMaster);
        //扣库存
        List<CartDTO> cartDTOList =  orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId() , e.getProductQuantity())).
                collect(Collectors.toList());
        productInfoService.decrease(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster , orderDTO);
        orderDTO.setOrderDetailList(detailRepository.findByOrderId(orderId));
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.toOrderDTO(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList ,pageable ,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.toOrderDTO(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList ,pageable ,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        //判断订单状态
        if(! orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMaster = OrderMaster2OrderDTOConverter.toOrderMaster(orderDTO);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if(orderDTO.getOrderDetailList().isEmpty() || orderDTO.getOrderDetailList().size() <= 0){
            log.error("【取消订单】订单中无商品详情, orderDTO={}",orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increase(cartDTOList);
        //如果已支付，退款
        if(orderDTO.getPayStatus() == PayStatusEnum.SUCCESS.getCode()){
            //TODO
        }
        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(! orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = OrderMaster2OrderDTOConverter.toOrderMaster(orderDTO);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if(! orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【完成支付订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断订单支付状态
        if(! orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【完成支付订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw  new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改订单支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = OrderMaster2OrderDTOConverter.toOrderMaster(orderDTO);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完成支付订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
