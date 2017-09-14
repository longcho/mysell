package cn.longzzai.controller;

import cn.longzzai.VO.ResultVO;
import cn.longzzai.dto.OrderDTO;
import cn.longzzai.enums.ResultEnum;
import cn.longzzai.exception.SellException;
import cn.longzzai.form.OrderForm;
import cn.longzzai.service.BuyerService;
import cn.longzzai.service.OrderService;
import cn.longzzai.service.WebSocket;
import cn.longzzai.utils.ResultVOUtil;
import cn.longzzai.utils.converter.OrderForm2OrderDTOConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家订单管理
 *
 * @author longcho
 * 2017-09-01-9:50
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private WebSocket webSocket;


    //创建订单
    @PostMapping("/create")

    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        webSocket.sendMsg("有一条新订单");
        return ResultVOUtil.success(map);
    }
    //查询订单列表
    @GetMapping("/list")

    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid ,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size" ,defaultValue = "10") int size){
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, new PageRequest(page, size));
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    //查询单个订单
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid ,
                                     @RequestParam("orderId") String orderId ){
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid ,
                           @RequestParam("orderId") String orderId){
       buyerService.cancelOrder(openid ,orderId);
        return ResultVOUtil.success();
    }
}
