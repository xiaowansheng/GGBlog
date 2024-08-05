package com.wbxnl.blog.api.admin.model.res;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 11:18
 */
@Data
public class TagDetailRes {

    private Integer id;

    private String tagKey;

    private String name;

    private String description;

    private Integer articleCount;

    private List<Integer> articleIds;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
