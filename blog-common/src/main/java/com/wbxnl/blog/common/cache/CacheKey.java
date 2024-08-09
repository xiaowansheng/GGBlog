package com.wbxnl.blog.common.cache;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/29 9:46
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class CacheKey {

    private static final String VERIFICATION_CODE="user:verificationCode:";

    private static final String USER_LOGIN_INFO="user:loginInfo:";

    private static final String USER_CAPTCHA="user:captcha:";

    /**
     * 获取key
     * @param email 用户邮箱
     * @return key
     */
    public static String getVerificationKey(String email) {
        return VERIFICATION_CODE + email;
    }

    /**
     * 获取key
     * @param username 用户名
     * @return key
     */
    public static String getLoginInfoKey(String username) {
        return USER_LOGIN_INFO + username;
    }

    /**
     * 获取key
     * @param email 用户邮箱
     * @return key
     */
    public static String getCaptchaKey(String email) {
        return USER_CAPTCHA + email;
    }
}
