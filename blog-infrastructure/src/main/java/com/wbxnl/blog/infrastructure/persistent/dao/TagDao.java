package com.wbxnl.blog.infrastructure.persistent.dao;

import com.wbxnl.blog.infrastructure.persistent.dao.base.BaseDao;
import com.wbxnl.blog.infrastructure.persistent.po.Tag;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 文章标签 Mapper 接口
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Mapper
public interface TagDao extends BaseDao<Tag> {
//    /**
//     * 分页获取分类详细数据
//     * @param current
//     * @param limit
//     * @param tagParams
//     * @return
//     */
//    List<TagDto> getDetailPage(@Param("current") Long current, @Param("limit") Long limit, @Param("tag")TagParams tagParams);
//
//
//    /**
//     * 用户获取全部分类详细数据
//     * @return
//     */
//    List<TagDto> getAllDetailByUser(@Param("params")ArticleParams params);

}
