package cn.longzzai.service;

import cn.longzzai.config.WechartConfig;
import cn.longzzai.config.WechartMPAccountConfig;
import cn.longzzai.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * @author longcho
 * 2017-09-11-15:32
 */
@Service
@Slf4j
public class SellerTemplateMessageServiceImpl implements SellerTemplateMessageService {
    @Autowired
    private WechartMPAccountConfig wechartMPAccountConfig;
    @Autowired
    private WxMpService wxMpService;

    @Override
    public void pushOrderFinishMessage(OrderDTO orderDTO) {
        try {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setToUser(orderDTO.getBuyerOpenid());
        templateMessage.setTemplateId(wechartMPAccountConfig.getMpTemplateId());

        templateMessage.getData().add(new WxMpTemplateData("first", "亲，请记得收货"));
        templateMessage.getData().add(new WxMpTemplateData("keyword1", "微信点餐"));
        templateMessage.getData().add(new WxMpTemplateData("keyword2", "17683112898"));
        templateMessage.getData().add(new WxMpTemplateData("keyword3", orderDTO.getOrderId()));
        templateMessage.getData().add(new WxMpTemplateData("keyword4", orderDTO.getPayStatusEnum().getMessage()));
        templateMessage.getData().add(new WxMpTemplateData("keyword5", "￥"+ orderDTO.getOrderAmount()));
        templateMessage.getData().add(new WxMpTemplateData("keyword6",new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(orderDTO.getUpdateTime())));

            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息推送】 {}" , e);
        }
    }
}
