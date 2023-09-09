package com.wbxnl.blog.constant.keys;

/**
 * 查询前缀
 * 用于拼接后续的字符串
 * 该key主要用于查询redis中的数据
 *
 * @Author xiaowansheng
 * @Date 2023/8/10 15:06
 */
public abstract class UserPrefix {
    /**
     * 查询验证码前缀
     */
    private static String VERIFICATION_CODE ="user:code:";
    /**
     * 查询用户信息前缀
     */
    private static String USER_INFO ="user:info:";

    /**
     * 获取查询用户信息的key
     * @param username
     * @return
     */
    public static String getUserInfoKey(String username){
        return USER_INFO+username;
    }

    /**
     * 获取查询验证码的key
     * @param email
     * @return
     */
    public static  String getCodeKey(String email){
        return VERIFICATION_CODE+email;
    }
}
