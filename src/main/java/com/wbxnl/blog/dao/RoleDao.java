package com.wbxnl.blog.dao;

import com.wbxnl.blog.model.dto.RoleResourceDto;
import com.wbxnl.blog.model.entity.Role;
import com.wbxnl.blog.dao.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Mapper
public interface RoleDao extends BaseDao<Role> {
    /**
     * 查询各个角色对应的资源列表
     * @return
     */
    List<RoleResourceDto> roleSources();
}
