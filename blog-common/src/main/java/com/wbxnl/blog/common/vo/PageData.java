package com.wbxnl.blog.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/14 5:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageData<T> {

    private Integer number;

    private Integer size;

    private Integer total;

    private List<T> data;
}
