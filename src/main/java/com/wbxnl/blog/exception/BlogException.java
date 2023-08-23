package com.wbxnl.blog.exception;

import com.wbxnl.blog.enums.OperationStateCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author wansheng
 * @createDate 2022/8/27 18:18
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class BlogException extends RuntimeException{
    /**
     * 错误码
     */
    public Integer code;
    /**
     * 错误信息
     */
    public String message;
    public BlogException(OperationStateCode operationStateCode){
        this.code= operationStateCode.getCode();
        this.message= operationStateCode.getMessage();
    }

    public BlogException(OperationStateCode operationStateCode, String message){
        this.code= operationStateCode.getCode();
        this.message=message;
    }
}
