package cn.longzzai.controller;

import cn.longzzai.Constant.Constant;
import cn.longzzai.config.ProjectUrlConfig;
import cn.longzzai.dataobject.SellerInfo;
import cn.longzzai.enums.ResultEnum;
import cn.longzzai.service.SellerService;
import cn.longzzai.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author longcho
 * 2017-09-10-20:58
 */
@Controller
@RequestMapping("seller/user")
public class SellerUserController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {
        try {
            SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
            //1. openid去和数据库里的数据匹配
            if (sellerInfo !=null){
                //2. 设置token至redis
                String token = UUID.randomUUID().toString();
                stringRedisTemplate.opsForValue().set(token ,openid ,7200 , TimeUnit.SECONDS);

                //3. 设置token至cookie
                CookieUtil.set(response , Constant.COOKIE_NAME_TOKEN ,token ,7200);
            }
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("reurl", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.LOGIN_SUCCESS.getMsg());
        map.put("reurl", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/logout")
    public ModelAndView login(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Map<String, Object> map) {

        Cookie cookie = CookieUtil.get(request, Constant.COOKIE_NAME_TOKEN);
        if(cookie !=null){
            stringRedisTemplate.opsForValue().getOperations().delete(Constant.COOKIE_NAME_TOKEN);
            CookieUtil.set(response ,Constant.COOKIE_NAME_TOKEN  , null ,0);

        }
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMsg());
        map.put("reurl", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);

    }
}
