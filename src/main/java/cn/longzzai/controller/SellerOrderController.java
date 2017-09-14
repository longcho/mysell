package cn.longzzai.controller;

import cn.longzzai.dto.OrderDTO;
import cn.longzzai.enums.ResultEnum;
import cn.longzzai.exception.SellException;
import cn.longzzai.service.BuyerService;
import cn.longzzai.service.OrderService;
import cn.longzzai.service.SellerTemplateMessageService;
import cn.longzzai.service.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家端订单管理
 *
 * @author longcho
 * 2017-09-07-19:29
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private SellerTemplateMessageService sellerTemplateMessageService;


    @GetMapping("/list")

    public ModelAndView list(@RequestParam(value = "page" , defaultValue = "1") int page ,
                             @RequestParam(value = "size" , defaultValue = "10") int size , Map<String ,Object> map){
        PageRequest request = new PageRequest(page - 1 ,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage" , page);
        map.put("size" , size);
        map.put("page" , orderDTOPage.getTotalPages());
        return new ModelAndView("order/list" ,map);
    }

    @GetMapping("/cancel")
    @CacheEvict(cacheNames = "sellOrder" , key ="111111")
    public ModelAndView cancel(@RequestParam("orderId") String orderId ,  Map<String ,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (Exception e) {
            log.error("【卖家取消订单】 ，错误");
            map.put("msg" , e.getMessage());
            map.put("reurl" , "/sell/seller/order/list");
            return new ModelAndView("common/error" ,map);
        }
        map.put("msg" , ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        map.put("reurl" , "/sell/seller/order/list");
        return new ModelAndView("common/success" ,map);
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId ,  Map<String ,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            map.put("orderDTO" ,orderDTO);
        }catch (SellException e) {
            log.error("【卖家端查询订单详情】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("reurl", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        return new ModelAndView("/order/detail" ,map);
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId ,  Map<String ,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
            map.put("orderDTO" ,orderDTO);
        }catch (SellException e) {
            log.error("【卖家端查询订单详情】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("reurl", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        //模板消息推送
        sellerTemplateMessageService.pushOrderFinishMessage(orderDTO);

        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        map.put("reurl", "/sell/seller/order/list");
        return new ModelAndView("/common/success" ,map);
    }
}
