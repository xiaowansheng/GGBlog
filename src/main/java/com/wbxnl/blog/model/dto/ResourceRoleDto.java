package com.wbxnl.blog.model.dto;

//import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author wansheng
 * @createDate 2022/8/28 0:47
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResourceRoleDto {
    /**
     * 资源id
     */
    private Integer id;

    /**
     * 路径
     */
    private String path;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 是否是开放接口
     */
    private Integer open;

    /**
     * 角色名
     */
    private List<String> roleList;

}
