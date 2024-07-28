package com.wbxnl.blog.domain.user.model.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/21 1:07
 */
@Data
@Builder
public class LoginLogVo {

    private String username;

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

    private byte[] point;

    private String location;
}
