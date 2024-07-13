package com.wbxnl.blog.common.vo;

import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/14 4:49
 */
@Data
public class PageParams {
    /**
     * 当前页码
     */
    private Integer number;
    /**
     * 每页数量
     */
    private Integer size;
    /**
     * 排序字段
     */
    private String sort;
    /**
     * 排序方式
     */
    private String order;
}
