package com.wbxnl.blog.infrastructure.persistent.dao;

import com.wbxnl.blog.infrastructure.persistent.dao.base.BaseDao;
import com.wbxnl.blog.infrastructure.persistent.po.LoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 登录日志，记录用户的登录信息 Mapper 接口
 * </p>
 *
 * @author wansheng
 * @since 2023-08-07
 */
@Mapper
public interface LoginLogDao extends BaseDao<LoginLog> {

}
