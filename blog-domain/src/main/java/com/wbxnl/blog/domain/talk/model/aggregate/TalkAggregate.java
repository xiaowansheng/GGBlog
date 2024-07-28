package com.wbxnl.blog.domain.talk.model.aggregate;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/15 22:39
 */
@Data
public class TalkAggregate {
    private Integer id;

    private String talkKey;

    private String username;

    private String content;

    private String images;

    private String status;

    private Integer top;

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

    private byte[] point;

    private String location;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer wordCount;

    private Integer readCount;

    private Integer commentCount;

    private Integer likeCount;
}
