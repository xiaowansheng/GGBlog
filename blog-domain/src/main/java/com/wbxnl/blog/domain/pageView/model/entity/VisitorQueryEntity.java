package com.wbxnl.blog.domain.pageView.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 11:38
 */
@Data
public class VisitorQueryEntity {
    private Integer id;

    private String uuid;

    private String viewType;

    private Integer viewId;

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

    private byte[] point;

    private Integer distance;

    private String location;

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;
}
