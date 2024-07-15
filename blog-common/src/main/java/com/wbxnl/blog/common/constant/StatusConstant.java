package com.wbxnl.blog.common.constant;

import javax.swing.plaf.PanelUI;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/16 0:29
 */
public class StatusConstant {

    private StatusConstant() {
    }


    public static class Article{

        private Article() {
        }

        /**
         * 开放
         */
        public static final Integer OPEN= 1;
        /**
         * 私密
         */
        public static final Integer PRIVATE= 2;
        /**
         * 登陆可见
         */
        public static final Integer LOGIN_VISIBLE= 3;
        /**
         * 评论可见
         */
        public static final Integer COMMENT_VISIBLE= 4;
        /**
         * 输入密码可见
         */
        public static final Integer PASSWORD_VISIBLE= 5;
    }
}
