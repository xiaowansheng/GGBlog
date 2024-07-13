package com.wbxnl.blog.infrastructure.persistent.dao;

import com.wbxnl.blog.infrastructure.persistent.dao.base.BaseDao;
import com.wbxnl.blog.infrastructure.persistent.po.Guestbook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 留言簿 Mapper 接口
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Mapper
public interface GuestbookDao extends BaseDao<Guestbook> {
//    /**
//     * 获取详细的留言信息
//     * @param page
//     * @param current
//     * @param queryParams
//     * @return
//     */
//    List<GuestbookDto> getPageByUser(@Param("page") Long page,@Param("current") long current,@Param("params") GuestbookParams queryParams);
}
