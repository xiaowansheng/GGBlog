package com.wbxnl.blog.domain.authority.model.eneity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/18 17:35
 */
@Data
public class UserResourceEntity {

//    private String name;

    private String resourceKey;

    private String requestMethod;

    private String path;

    private Integer parentId;

}
