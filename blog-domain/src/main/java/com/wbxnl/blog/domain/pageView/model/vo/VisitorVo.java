package com.wbxnl.blog.domain.pageView.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 11:28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitorVo {

    private String uuid;

    private String viewType;

    private Integer viewId;

    private String ipAddress;

    private String ipSource;

    private String device;

    private String browser;

    private byte[] point;

    private String location;
}
