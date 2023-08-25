package com.wbxnl.blog.model.dto;

import java.io.Serializable;
import java.util.Date;
//import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.experimental.Accessors;

/**
 * <p>
 * 网站配置
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "SystemConfigDto",title = "SystemConfigDto",description = "响应的网站配置信息")
public class SystemConfigDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String label;

    private String value;

    private String description;

    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    private Integer deleted;
}
