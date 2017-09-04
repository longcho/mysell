package cn.longzzai.service;

import cn.longzzai.dto.OrderDTO;

/**
 * @author longcho
 * 2017-09-02-16:30
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
