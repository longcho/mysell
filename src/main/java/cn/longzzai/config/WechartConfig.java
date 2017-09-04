package cn.longzzai.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 微信配置
 *
 * @author longcho
 * 2017-09-04-10:48
 */
@Component
public class WechartConfig {
    @Autowired
    private WechartMPAccountConfig wechartMPAccountConfig;
    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
    }
    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(wechartMPAccountConfig.getAppId());
        wxMpConfigStorage.setSecret(wechartMPAccountConfig.getSecret());
        return wxMpConfigStorage;
    }
}
