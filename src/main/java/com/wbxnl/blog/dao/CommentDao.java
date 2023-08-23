package com.wbxnl.blog.dao;

import com.wbxnl.blog.model.dto.CommentDto;
import com.wbxnl.blog.model.entity.Comment;
import com.wbxnl.blog.dao.base.BaseDao;
import com.wbxnl.blog.model.vo.CommentVo;
import com.wbxnl.blog.model.vo.params.CommentParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 博客评论 Mapper 接口
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Mapper
public interface CommentDao extends BaseDao<Comment> {
    /**
     * 查询根评论的数量
     * 【用作分页展示时的数据总数】
     * @param commentParams
     * @return
     */
    Long getRootCountByUser(@Param("comment") CommentParams commentParams);

    /**
     * 用户分页查询评论信息
     * @param current
     * @param limit
     * @param commentParams
     * @return
     */
    List<CommentDto> getPageByUser(@Param("current") long current, @Param("limit") long limit, @Param("comment") CommentParams commentParams);


    /**
     * 用户根据条件查询评论的数量
     * @param commentParams
     * @return
     */
    Long getCountByUser(@Param("comment") CommentParams commentParams);

    /**
     * 根据ID获取详细的评论信息
     * 【用做评论成功后的评论信息返回】
     * @param commentId
     * @return
     */
    CommentDto getComment(@Param("id")Integer commentId);

    /**
     * 分页查询详细评论信息
     * @param current
     * @param limit
     * @param commentParams
     * @return
     */
    List<CommentDto> getPage(@Param("current") long current,@Param("limit") long limit, @Param("comment") CommentParams commentParams);

    /**
     * 根据条件获取评论数量
     * @param commentParams
     * @return
     */
    Long getCount(@Param("comment") CommentParams commentParams);
}
