package com.wbxnl.blog.domain.pageView.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 11:06
 */
@Data
public class PageViewEntity {

    private Integer id;

    private Long count;

    private String viewType;

    private Integer viewKey;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
