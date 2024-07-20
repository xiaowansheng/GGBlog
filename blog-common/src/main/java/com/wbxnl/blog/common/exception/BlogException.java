package com.wbxnl.blog.common.exception;

import com.wbxnl.blog.common.enums.OperationCodeEnum;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/20 16:46
 */
public class BlogException extends RuntimeException{

    private Integer code;

    private String message;

    public BlogException(OperationCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.name();
    }
}
