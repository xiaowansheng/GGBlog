package com.wbxnl.blog.api.admin.model.res;

import lombok.Builder;
import lombok.Data;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 10:52
 */
@Data
@Builder
public class NumberStatisticsRes {

    private Long articleCount;

    private Long categoryCount;

    private Long tagCount;
}
