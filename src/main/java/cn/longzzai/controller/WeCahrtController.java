package cn.longzzai.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author longcho
 * 2017-09-03-10:01
 */
@Controller
@RequestMapping("/wechart")
@Slf4j
public class WeCahrtController {
    @Autowired
    private WxMpService wxMpService;

        //微信接口url认证
    @ResponseBody
    @RequestMapping("/aaaa")
    public String urlAuth(@RequestParam("signature") String signature ,
                          @RequestParam("timestamp") String timestamp ,
                          @RequestParam("nonce") String nonce ,
                          @RequestParam("echostr") String echostr ){
        return null;
    }

    @GetMapping("/auth")
    @ResponseBody
    public String authGetCode(@RequestParam("code") String code){
        log.info("【微信授权】 ，code={}", code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx079d0593a06aa2e4&secret=c77a29e4c3098e8c66f4bd95c0b633a8&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);
        log.info("【微信授权】 ， response={}", forObject);
        return forObject ;
    }

    @GetMapping("/auth/token")
    public String authGetToken(@RequestParam("code") String code){
        log.info("【微信授权】 ，code={}", code);
        return code;
    }
    //获取openid
    @GetMapping("/authorize")
    @ResponseBody
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        //配置参数
        //执行
        String url = wxMpService.oauth2buildAuthorizationUrl(returnUrl, WxConsts.OAUTH2_SCOPE_USER_INFO, null);
        log.info("【微信认证】获取openid ， url={}",url);
        return url;
    }
}
