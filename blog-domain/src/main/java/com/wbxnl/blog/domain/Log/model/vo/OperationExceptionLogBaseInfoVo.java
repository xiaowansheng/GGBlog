package com.wbxnl.blog.domain.Log.model.vo;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 22:40
 */
@Data
public class OperationExceptionLogBaseInfoVo {

    private Integer id;

    private Integer userAuthId;

    private String userName;

    private String version;

    private String requestUrl;

    private String requestMethod;

    private String requestParam;

    private String module;

    private String callingMethod;

    private String errorName;

    private String errorMessage;

}
