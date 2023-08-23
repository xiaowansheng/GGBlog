package com.wbxnl.blog.model.dto;

//import io.swagger.annotations.ApiModel;
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
//@ApiModel(value = "Meta对象", description = "路由配置")
public class Meta  implements Serializable {


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
