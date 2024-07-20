package com.wbxnl.blog.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/16 0:58
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class TypeConstant {


    public interface Article {
        /**
         * 草稿
         */
        String DRAFT="draft";
        /**
         * 原创
         */
        String ORIGINAL="original";
        /**
         * 转载
         */
        String REPRINT="reprint";
        /**
         * 翻译
         */
        String TRANSLATION="translation";
    }
}
