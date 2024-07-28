package com.wbxnl.blog.domain.Log.model.vo;

import lombok.Data;


/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 22:40
 */
@Data
public class OperationExceptionLogBaseInfoVo {

    private Integer id;

    private String username;

    private String version;

    private String requestUrl;

    private String requestMethod;

    private String requestParam;

    private String module;

    private String callingMethod;

    private String errorName;

    private String errorMessage;

}
