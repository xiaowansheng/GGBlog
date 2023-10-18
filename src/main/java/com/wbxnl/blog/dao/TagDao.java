package com.wbxnl.blog.dao;

import com.wbxnl.blog.model.dto.TagDto;
import com.wbxnl.blog.model.entity.Tag;
import com.wbxnl.blog.dao.base.BaseDao;
import com.wbxnl.blog.model.vo.params.ArticleParams;
import com.wbxnl.blog.model.vo.params.TagParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    /**
     * 分页获取分类详细数据
     * @param current
     * @param limit
     * @param tagParams
     * @return
     */
    List<TagDto> getDetailPage(@Param("current") Long current, @Param("limit") Long limit, @Param("tag")TagParams tagParams);


    /**
     * 用户获取全部分类详细数据
     * @return
     */
    List<TagDto> getAllDetailByUser(@Param("params")ArticleParams params);

}
