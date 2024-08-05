package com.wbxnl.blog.api.admin.model.req;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 14:41
 */
@Data
public class TalkDataReq {

    private String username;

    private String content;

    private String images;

    private String status;

    private Integer top;

    private byte[] point;

//    private String location;
}
