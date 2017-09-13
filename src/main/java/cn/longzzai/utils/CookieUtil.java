package cn.longzzai.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author longcho
 * 2017-09-10-21:57
 */
public class CookieUtil {

    public static Cookie get(HttpServletRequest request , String cookieName){
        Map<String, Cookie> cookieMap = readCookieToMap(request);
        if (cookieMap !=null && cookieMap.containsKey(cookieName)){
            return cookieMap.get(cookieName);
        }
        return null;
    }

    public static void set(HttpServletResponse response ,String cookieName ,String value,int maxAge){
        Cookie cookie = new Cookie(cookieName ,value );
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 将cookie封装成Map
     * @param request
     * @return
     */
    private static Map<String, Cookie> readCookieToMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
