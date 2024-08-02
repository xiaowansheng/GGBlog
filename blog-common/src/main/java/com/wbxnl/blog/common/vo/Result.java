package com.wbxnl.blog.common.vo;

import com.wbxnl.blog.common.enums.OperationCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 10:05
 */
@Data
@AllArgsConstructor
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(OperationCodeEnum.SUCCESS.getCode(), "success", data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(OperationCodeEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> Result<T> failure(String message) {
        return new Result<>(OperationCodeEnum.FAILURE.getCode(), message, null);
    }

    public static <T> Result<T> failure(String message, T data) {
        return new Result<>(OperationCodeEnum.FAILURE.getCode(), message, data);
    }
}
