package com.wbxnl.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
//import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author wansheng
 * @createDate 2022/9/15 3:47
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "RouterDto", title = "RouterDto", description = "响应的路由菜单信息")
public class RouterDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    private Integer id;
    /**
     * 菜单名称
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;


    /**
     * 路由重定向
     */
    private String redirect;

    /**
     * 路由配置
     */
    private Meta meta;
    /**
     * 隐藏菜单（0展示1隐藏）
     */
    private Integer hidden;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 父类ID
     */
    private Integer parentId;

    /**
     * 权限标识
     */
    private String perms;


    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<RouterDto> children;


    // 重写equals和hashcode方法，用来加入Set集合去重复
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RouterDto routerDto = (RouterDto) o;

        return id.equals(routerDto.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
