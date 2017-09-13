package cn.longzzai.service;

import cn.longzzai.dto.OrderDTO;

/**商户模板消息
 * @author longcho
 * 2017-09-11-15:27
 */
public interface SellerTemplateMessageService {

    public void pushOrderFinishMessage(OrderDTO orderDTO);
}
