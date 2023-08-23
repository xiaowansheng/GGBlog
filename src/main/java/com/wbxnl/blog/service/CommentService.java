package com.wbxnl.blog.service;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.model.dto.CommentDto;
import com.wbxnl.blog.model.entity.Comment;
import com.wbxnl.blog.model.vo.CommentVo;
import com.wbxnl.blog.model.vo.params.CommentParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.service.base.BaseService;
import org.springdoc.core.annotations.ParameterObject;

import java.util.Map;

/**
 * <p>
 * 博客评论 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface CommentService extends BaseService<Comment, CommentDto, CommentVo> {

    /**
     * 获取待审核的评论列表
     *
     * @param pageParams
     * @param commentParams
     * @return
     */
    PageData<CommentDto> getReviews(PageParams pageParams, CommentParams commentParams);

    /**
     * 用户添加评论，并返回添加的评论的详细信息
     *
     * @param vo
     * @return
     */
    CommentDto saveVoByUser(CommentVo vo);

    /**
     * 用户查询评论数
     *
     * @param topicType
     * @param topicId
     * @return
     */
    Long getCountByUser(String topicType, Integer topicId);
}
