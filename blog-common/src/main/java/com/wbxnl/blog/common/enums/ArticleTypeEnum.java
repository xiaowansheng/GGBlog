package com.wbxnl.blog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/25 16:44
 */
@Getter
@AllArgsConstructor
public enum ArticleTypeEnum {
    DRAFT("draft"),
    ORIGINAL("original"),
    REPRINT("reprint"),
    TRANSLATION("translation"),
    ;
    /**
     * 文章类型
     */
    private String type;
}
