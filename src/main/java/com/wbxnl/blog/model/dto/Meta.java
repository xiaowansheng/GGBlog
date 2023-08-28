package com.wbxnl.blog.model.dto;

//import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wansheng
 * @createDate 2022/9/15 4:15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "Meta",title = "Meta",description = "响应的菜单路由配置信息")
public class Meta  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图标
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
    /**
     * 隐藏菜单
     */
    private Boolean hideMenu;
    /**
     * 隐藏子类菜单
     */
    private boolean hideChildrenInMenu;
}
