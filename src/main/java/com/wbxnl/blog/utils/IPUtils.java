package com.wbxnl.blog.utils;

import com.alibaba.fastjson2.JSON;
import eu.bitwalker.useragentutils.UserAgent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.servlet.http.HttpServletRequest;

/**
 * ip工具类
 *
 * @author 11921
 */
@SuppressWarnings("all")
public class IPUtils {
//    private static final String API_URL = "http://ip.taobao.com/service/getIpInfo.php?ip=%s";
//    public static String parseProvince(String ip) {
//        String url = String.format(API_URL, ip);
//        try (BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
//            String response = in.lines().findFirst().orElse("");
//            JSONObject json = JSONObject.parseObject(response);
//            if (json.getIntValue("code") == 0) {
//                JSONObject data = json.getJSONObject("data");
//                String province = data.getString("region");
//                return province;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    private static String extractProvince(String pattern,String str){
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.group();
        }
        return null;
    }

    /**
     * 从地址字符串中找出省份
     * @param str
     * @return
     */
    public static String extractProvince(String str) {
        if(str==null){
            return null;
        }
        String province=null;
        String pattern = ".*?(省)";
        province=extractProvince(pattern,str);
        if(province!=null){
            return province;
        }
        pattern = ".*?(自治区)";
        province=extractProvince(pattern,str);
        if(province!=null){
            return province;
        }
        pattern = ".*?(市)";
        province=extractProvince(pattern,str);
        if(province!=null){
            return province;
        }
        return null;
    }

    /**
     * 获取用户ip地址
     *
     * @param request 请求
     * @return ip地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if ("127.0.0.1".equals(ipAddress)) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

    /**
     * 解析ip地址
     *
     * @param ipAddress ip地址
     * @return 解析后的ip地址
     */
    public static String getIpSource(String ipAddress) {
        try {
            URL url = new URL("http://opendata.baidu.com/api.php?query=" + ipAddress + "&co=&resource_id=6006&oe=utf8");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "utf-8"));
            String line = null;
            StringBuffer result = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            Map map = JSON.parseObject(result.toString(), Map.class);
            List<Map<String, String>> data = (List) map.get("data");
            return data.get(0).get("location");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取访问设备
     *
     * @param request 请求
     * @return {@link UserAgent} 访问设备
     */
    public static UserAgent getUserAgent(HttpServletRequest request){
        return UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
    }

}
