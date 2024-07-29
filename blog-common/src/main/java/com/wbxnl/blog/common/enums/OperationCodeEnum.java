package com.wbxnl.blog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/20 16:53
 */
@Getter
@AllArgsConstructor
public enum OperationCodeEnum {
    // 操作成功提示码
    SUCCESS(200),

    // 操作失败提示码
    // 40000~49999
    FAILURE(40000),
    // 40100-40199:文章领域错误
    CATEGORY_EXISTS(40101),
    TAG_EXISTS(40102),
    // 40200-40299:分类领域错误

    // 40300-40399:标签领域错误

    // 40400-40499:用户领域错误

    PASSWORD_ERROR(40401),
    VERIFICATION_CODE_ERROR(40402),
    TOKEN_ERROR(40403),
    USER_DISABLE(40404)
    // 40500-40599:评论领域错误

    // 40600-40699:友链领域错误

    // 40700-40799:友链分类领域错误

    // 40800-40899:广告领域错误
    ;
    @Getter
    private final Integer code;
}
