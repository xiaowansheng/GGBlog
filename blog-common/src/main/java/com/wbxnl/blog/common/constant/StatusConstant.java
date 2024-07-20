package com.wbxnl.blog.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/16 0:29
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class StatusConstant {


    public interface Article{
        /**
         * 开放
         */
        Integer OPEN= 1;
        /**
         * 私密
         */
        Integer PRIVATE= 2;
        /**
         * 登陆可见
         */
        Integer LOGIN_VISIBLE= 3;
        /**
         * 评论可见
         */
        Integer COMMENT_VISIBLE= 4;
        /**
         * 输入密码可见
         */
        Integer PASSWORD_VISIBLE= 5;
    }
}
