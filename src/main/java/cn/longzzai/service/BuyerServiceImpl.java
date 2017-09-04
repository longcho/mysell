package cn.longzzai.service;

import cn.longzzai.dto.OrderDTO;
import cn.longzzai.enums.ResultEnum;
import cn.longzzai.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author longcho
 * 2017-09-02-16:34
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderIdAndFindOne(openid , orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderIdAndFindOne(openid, orderId);
        if (orderDTO == null){
            log.error("【取消订单】查不到该订单, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    //检查用户openid是否正确,并找出订单
    public OrderDTO checkOrderIdAndFindOne(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null || orderId == ""){
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equals(openid)) {
            log.error("【查询订单】订单openid与当前用户不同 ，openid={}",openid);
            throw new SellException(ResultEnum.BUYER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
