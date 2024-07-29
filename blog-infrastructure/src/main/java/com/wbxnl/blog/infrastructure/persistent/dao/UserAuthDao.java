package com.wbxnl.blog.infrastructure.persistent.dao;

import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.user.model.aggregate.UserDetailAggregate;
import com.wbxnl.blog.domain.user.model.entity.UserQueryEntity;
import com.wbxnl.blog.infrastructure.persistent.dao.base.BaseDao;
import com.wbxnl.blog.infrastructure.persistent.po.UserAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户账号 Mapper 接口
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Mapper
public interface UserAuthDao extends BaseDao<UserAuth> {

    List<UserDetailAggregate> getPageUserDetails(@Param("pageParams") PageParams pageParams,@Param("userQueryEntity") UserQueryEntity userQueryEntity);

    Long getPageUserDetailsTotal(@Param("userQueryEntity") UserQueryEntity userQueryEntity);
}
