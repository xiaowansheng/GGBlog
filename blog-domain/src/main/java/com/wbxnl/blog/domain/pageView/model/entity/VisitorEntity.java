package com.wbxnl.blog.domain.pageView.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 11:15
 */
@Data
public class VisitorEntity {

    private Integer id;

    private String uuid;

    private String viewType;

    private Integer viewKey;

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

    private byte[] point;

    private String location;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
