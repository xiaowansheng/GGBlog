package com.wbxnl.blog.domain.talk.model.entity;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/15 22:34
 */
@Data
public class TalkQueryEntity {

    private Integer id;

    private String talkKey;

    private String content;

    private String images;

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
