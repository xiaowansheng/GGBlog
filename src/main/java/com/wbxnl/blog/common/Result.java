/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.wbxnl.blog.common;

import com.wbxnl.blog.enums.OperationStateCode;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应数据
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
//@ApiModel(value = "响应")
@Data
@Accessors(chain = true)
@Schema(title = "Result",description = "响应结果")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编码：0表示成功，其他值表示失败
     */
    ////@ApiModelProperty(value = "响应码：200表示成功，其他值表示失败")
    @Schema(title = "响应码")
    private int code = OperationStateCode.SUCCESS.getCode();
    /**
     * 消息内容
     */
    ////@ApiModelProperty(value = "消息内容")
    @Schema(title = "响应信息")
    private String message = "success";
    /**
     * 响应数据
     */
    ////@ApiModelProperty(value = "响应数据")
    @Schema(title = "响应数据")
    private T data;

    public Result<T> ok(T data) {
        this.setData(data);
        return this;
    }

    public Result<T> ok(String message) {
//        this.setCode();
        this.setMessage(message);
        return this;
    }

    public Result<T> ok(String message, T data) {
        this.setMessage(message);
        this.data = data;
        return this;
    }

    public boolean success() {
        return code == 200;
    }

    public Result<T> error() {
        this.code = OperationStateCode.FAILURE.getCode();
        this.message = "服务器错误!!!";
        return this;
    }

    public Result<T> error(int code, String msg) {
        this.code = code;
        this.message = msg;
        return this;
    }


    public Result<T> error(int code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
        return this;
    }

    public Result<T> error(OperationStateCode operationStateCode) {
        this.code = operationStateCode.getCode();
        this.message = operationStateCode.getMessage();
        return this;
    }
}
