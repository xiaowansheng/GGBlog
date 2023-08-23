package com.wbxnl.blog.dao;

import com.wbxnl.blog.model.entity.LogOperation;
import com.wbxnl.blog.dao.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 操作日志 Mapper 接口
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Mapper
public interface LogOperationDao extends BaseDao<LogOperation> {

}
