package com.wbxnl.blog.domain.leaveMessage.model.vo;

import lombok.Data;



/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 0:05
 */
@Data
public class LeaveMessageInsertVo {

    private Integer userAuthId;

    private String content;

    private String images;

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

    private byte[] point;

    private String location;

    private String type;

    private String nickname;

    private String email;

    private String qq;

}
