package cn.longzzai.controller;

import cn.longzzai.enums.ResultEnum;
import cn.longzzai.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

/**
 * @author longcho
 * 2017-09-03-10:01
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeCahrtController {
    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/openId")
    public String openId(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl){
        log.info("【微信授权】 ，code={} ,state={}", code ,returnUrl);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken =new WxMpOAuth2AccessToken();
        WxMpUser wxMpUser =new WxMpUser();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信授权】，获取accesstoken失败  e={}" ,e);
            throw new SellException(ResultEnum.WECHAT_TOKEN_ERROR.getCode() , e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        String redirect =  returnUrl + "?openid=" + openId;
        log.info("【微信授权】，redirect={}" , redirect);
        return "redirect:" + redirect;
    }


    //获取openid
    @GetMapping("/authorize")
    public String authorize(@RequestParam(value = "returnUrl" , required = true) String returnUrl){
        //配置参数
        //执行
        /**
         * @param url :下一步跳转url
         */
        String url = "http://longzzai.natapp4.cc/sell/wechat/openId";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_BASE, returnUrl);
        log.info("【微信认证】获取openid的code ， url={}",redirectUrl);
        return "redirect:"+ redirectUrl;
    }
}
