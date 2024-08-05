package com.wbxnl.blog.api.admin.model.req;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/4 16:34
 */
@Data
public class OperationErrorLogQueryReq {

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

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;
}
