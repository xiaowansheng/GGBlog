package com.wbxnl.blog.api.admin.model.res;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 14:44
 */
@Data
public class TalkDetailRes {

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
