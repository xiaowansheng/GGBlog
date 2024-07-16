package com.wbxnl.blog.domain.talk.model.vo;

import lombok.Data;



/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/15 22:20
 */
@Data
public class TalkVo {

    private Integer userAuthId;

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
}
