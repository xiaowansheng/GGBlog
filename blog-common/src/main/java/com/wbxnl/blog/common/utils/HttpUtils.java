package com.wbxnl.blog.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 14:58
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class HttpUtils {
    /**
     * 获取请求的ip地址
     * @param request 请求
     * @return ip地址
     */
    public static String getIpAddress(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
        return "127.0.0.1";
    }

    /**
     * 获取ip来源
     * @param ip ip地址
     * @return ip来源
     */
    public static String getIpSource(String ip){
        return "UNKNOW";
    }

    /**
     * 获取请求的设备信息
     * @param request 请求
     * @return 设备
     */
    public static String getRequestDevice(HttpServletRequest request){

        return "UNKNOW";
    }

    /**
     * 获取请求的浏览器信息
     * @param request 请求
     * @return 浏览器
     */
    public static String getRequestBrowser(HttpServletRequest request){

        return "UNKNOW";
    }
}
