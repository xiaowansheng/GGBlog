package com.wbxnl.blog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtil {
    /**
     * 存放信息的key
     */
    public static final String INFO_KEY = "info";


    /**
     * 过期2小时
     */
    public static final long EXPIRE_TIME = 2*60 * 60 * 1000;

    /**
     * 刷新token 14天过期，14天不操作必须重新登录一次
     */
    public static final long REFRESH_EXPIRE_TIME = 14*24*60 * 60 * 1000;

    /**
     * jwt密钥
     */
    private static final String SECRET = "xiaowansheng";

    /**
     * 生成jwt字符串， JWT(json web token)
     *
     * @param username
     * @param info,Map的value只能存放值的类型为：Map，List，Boolean，Integer，Long，Double，String and Date
     * @return
     */
    public static String getToken(String username, Map<String, Object> info) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            //保存token失效时间
//            info.put(EXPIRE_KEY,date.getTime());
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    //将userId保存到token里面
                    .withAudience(username)
                    //存放自定义数据
                    .withClaim(INFO_KEY, info)
                    //xxx分钟后token过期
                    .withExpiresAt(date)
                    //token的密钥
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取刷新token
     *
     * @param username
     * @param info
     * @return
     */
    public static String getRefreshToken(String username, Map<String, Object> info) {
        try {
            Date date = new Date(System.currentTimeMillis() + REFRESH_EXPIRE_TIME);
            //保存token失效时间
//            info.put(EXPIRE_KEY,date.getTime());
//            log.info("设置的过期时间："+date.getTime());
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    //将userId保存到token里面
                    .withAudience(username)
                    //存放自定义数据
                    .withClaim(INFO_KEY, info)
                    //xxx分钟后token过期
                    .withExpiresAt(date)
                    //token的密钥
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据token获取userId
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            String userId = JWT.decode(token).getAudience().get(0);
            return userId;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 根据token获取自定义数据info
     *
     * @param token
     * @return
     */
    public static Map<String, Object> getInforMap(String token) {
        try {
            return JWT.decode(token).getClaim(INFO_KEY).asMap();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取token到期时间
     *
     * @param token
     * @return
     */
    public static long getExpireTime(String token) {
        return JWT.decode(token).getExpiresAt().getTime();
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public static boolean checkToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    //.withClaim("username, username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.warn("token 无效，请重新获取");
            return false;
        }
    }
}