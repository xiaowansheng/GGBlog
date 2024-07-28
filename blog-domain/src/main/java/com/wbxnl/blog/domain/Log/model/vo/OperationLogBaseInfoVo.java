package com.wbxnl.blog.domain.Log.model.vo;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 22:40
 */
@Data
public class OperationLogBaseInfoVo {


//    private String username;

//    private String version;

    private String requestUrl;

    private String module;

    private String callingMethod;

    private String type;

    private String description;

    private String requestMethod;

    private String requestParam;

    private String responseData;

    private Long elapsedTime;

//    private String ipAddress;
//
//    private String ipSource;
//
//    private String device;
//
//    private String browser;
}
