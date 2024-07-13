package com.wbxnl.blog.common.vo;

import lombok.Data;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/14 5:57
 */
@Data
public class PageData<T> {

    private Integer number;

    private Integer size;

    private Integer total;

    private List<T> data;
}
