package com.wbxnl.blog.infrastructure.persistent.dao;

import com.wbxnl.blog.infrastructure.persistent.dao.base.BaseDao;
import com.wbxnl.blog.infrastructure.persistent.po.Album;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 相册 Mapper 接口
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Mapper
public interface AlbumDao extends BaseDao<Album> {

}
