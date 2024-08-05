package com.wbxnl.blog.api.admin.model.req;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 17:06
 */
@Data
public class OperationLogQueryReq {

    private Integer id;

    private String username;

    private String version;

    private String requestUrl;

    private String module;

    private String callingMethod;

    private String type;

    private String description;

    private String requestMethod;

    private String requestParam;

    private String responseData;

    private Long elapsedTime;

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;
}
