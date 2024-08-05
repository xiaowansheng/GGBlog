package com.wbxnl.blog.api.admin.model.req;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 14:44
 */
@Data
public class TalkQueryReq {

    private Integer id;

    private String username;

    private String talkKey;

    private String content;

    private String status;

    private Integer top;

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

    private byte[] point;
    /**
     * 搜索坐标范围
     */
    private Integer distance;

    private String location;

    private LocalDateTime beginCreateTime;

    private LocalDateTime endCreateTime;

    private LocalDateTime beginUpdateTime;

    private LocalDateTime endUpdateTime;
}
