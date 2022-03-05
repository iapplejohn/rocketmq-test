package com.jemmy.rocketmq.test.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chengzhujiang
 * @since 2019/8/1
 */
public class CookieUtil {

    private final static Logger logger = LoggerFactory.getLogger(CookieUtil.class);

    /** 保存路径,根路径 */
    private static final String DEFAULT_COOKIE_PATH = "/";

    /** 默认缓存时间，单位为秒 */
    private static final int COOKIE_MAX_AGE = 7200;

    public static Cookie createCookie(String name, String v) {
        return createCookie(name, v, true);
    }

    public static Cookie createCookie(String name, String v, boolean base64) {
        return createCookie(name, v, base64, false);
    }

    public static Cookie createCookie(String name, String v, boolean base64, boolean ifRemember) {
        String value = v;
        if (base64) {
            value = encode(v);
        }
        int maxAge = ifRemember ? COOKIE_MAX_AGE : -1;

        return doCreateCookie(name, value, null, DEFAULT_COOKIE_PATH, maxAge, false);
    }

    /**
     * 创建cookie
     *
     * @param name 名称
     * @param value 值
     * @param domain 域名
     * @param path 路径
     * @param maxAge 有效期
     * @param isHttpOnly 是否仅仅HTTP
     * @return cookie对象
     */
    private static Cookie doCreateCookie(String name, String value, String domain,
        String path, int maxAge, boolean isHttpOnly) {

        Cookie cookie = new Cookie(name, value);
        if (domain != null) {
            cookie.setDomain(domain);
        }
        if (path != null) {
            cookie.setPath(path);
        }
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(isHttpOnly);
        return cookie;
    }

    private static String encode(String value) {
        if (StringUtils.isEmpty(value)) {
            return "";
        }

        try {
            String encodeVal = new String(Base64.encodeBase64(value.getBytes("UTF-8")));
            encodeVal = URLEncoder.encode(encodeVal, "UTF-8");

            logger.debug("加密 Cookie; origin : {} ; final :{}", value, encodeVal);
            return encodeVal;
        } catch (UnsupportedEncodingException e) {
            logger.error("加密 cookie 异常!", e);
            return null;
        }
    }

    /**
     * 获取cookie值
     *
     * @param request 请求
     * @param name cookie名称
     * @return cookie值
     */
    public static String getValue(HttpServletRequest request, String name) {
        Cookie cookie = get(request, name);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 获取cookie对象
     *
     * @param request 请求
     * @param name cookie名称
     * @return cookie对象
     */
    private static Cookie get(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 删除指定cookie
     *
     * @param request 请求
     * @param response 响应
     * @param name cookie名称
     */
    public static void remove(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie cookie = get(request, name);
        if (cookie != null) {
            Cookie cookieToRemove = doCreateCookie(name, "", null, DEFAULT_COOKIE_PATH, 0, false);
            response.addCookie(cookieToRemove);
        }
    }

    /**
     * 清理登录后写入的 cookie
     *
     * @param request ~
     * @param response ~
     */
    public static void cleanSessionCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("u", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

    }
}
