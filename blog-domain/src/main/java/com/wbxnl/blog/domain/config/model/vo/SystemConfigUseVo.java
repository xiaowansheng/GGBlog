package com.wbxnl.blog.domain.config.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 10:45
 */
@Data
@Accessors(chain = true)
public class SystemConfigUseVo {

    private String configKey;

    private String name;

    private String value;

}
