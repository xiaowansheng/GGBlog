package com.wbxnl.blog.constant.keys;

/**
 * 查询前缀
 * 用于拼接后续的字符串
 * 该key主要用于查询redis中的数据
 *
 * @Author xiaowansheng
 * @Date 2023/8/10 15:06
 */
public interface UserPrefix {
    /**
     * 查询验证码前缀
     */
    String VERIFICATION_CODE ="user:code:";
    /**
     * 查询用户信息前缀
     */
    String USER_INFO ="user:info:";
}
