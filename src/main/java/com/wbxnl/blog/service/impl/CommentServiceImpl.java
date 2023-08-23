package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.enums.TopicTypeEums;
import com.wbxnl.blog.enums.UserTypeEums;
import com.wbxnl.blog.model.dto.CommentDto;
import com.wbxnl.blog.model.entity.Comment;
import com.wbxnl.blog.dao.CommentDao;
import com.wbxnl.blog.model.vo.CommentVo;
import com.wbxnl.blog.model.vo.params.CommentParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.QueryParams;
import com.wbxnl.blog.service.*;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import com.wbxnl.blog.utils.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * <p>
 * 博客评论 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
@Slf4j
public class CommentServiceImpl extends AbstractServiceImpl<CommentDao, Comment, CommentDto, CommentVo> implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private EmailService emailService;

    @Override
    public PageData<CommentDto> getReviews(PageParams pageParams,CommentParams commentParams) {
        Long page = pageParams.getPage();
        Long limit = pageParams.getLimit();
        Page<Comment> commentPage = new Page<>(page, limit);
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Comment::getReview,0)
                .orderByDesc(Comment::getCreateTime);
        PageData<CommentDto> pageData = getPageData(commentPage, queryWrapper);
        return pageData;
    }


    @Override
    public Long getCountByUser(String topicType, Integer topicId) {
        // 获取审核通过的评论数量
        LambdaQueryChainWrapper<Comment> chainWrapper = lambdaQuery()
                .eq(Comment::getTopicType, topicType)
                .eq(Comment::getTopicId, topicId)
                // 审核通过
                .eq(Comment::getReview, 1)
                // 内容公开
                .eq(Comment::getHidden, 0);
        if(ObjectUtils.isNotNull(topicId)){
            chainWrapper
                    .eq(Comment::getTopicId, topicId);
        }
        return chainWrapper.count();
    }

    @Override
    public PageData<CommentDto> getPageByUser(PageParams pageParams, QueryParams queryParams) {
        CommentParams commentParams = (CommentParams) queryParams;
        Long page = pageParams.getPage();
        Long limit = pageParams.getLimit();
        List<CommentDto> dtos = commentDao.getPageByUser((page - 1) * limit, limit, commentParams);
        List<CommentDto> commentDtos = buildTree(dtos, null, 0);
        Long count = commentDao.getRootCountByUser(commentParams);
        return new PageData<>(commentDtos, count);
    }

    /**
     * 把获取的评论列表，转换成树形结构
     * TODO 还能优化
     * @param list
     * @param root
     * @param deep
     * @return
     */
    private List<CommentDto> buildTree(List<CommentDto> list, List<CommentDto> root, int deep) {
        ArrayList<CommentDto> commentDtos = new ArrayList<>();
        if (deep == 0) {
            list.forEach(dto -> {
                if (dto.getParentId() == 0) {
                    commentDtos.add(dto);
                }
            });
        } else {
            root.forEach(dto -> {
                ArrayList<CommentDto> children = new ArrayList<>();
                list.forEach(dto2 -> {
//                    log.info("root:"+dto.getId());
//                    log.info("children:"+dto2.getRootId());
//                    log.info("判断："+(dto.getId().equals(dto2.getRootId())));
                    if (dto.getId().equals(dto2.getRootId())) {
                        list.forEach(e -> {
                            if (e.getId() == dto2.getParentId()) {
                                dto2.setReplyUserName(e.getUserName());
                            }
                        });
                        children.add(dto2);
                    }
                });
                if (children.size() > 0) {
                    Collections.sort(children, new Comparator<CommentDto>() {
                        @Override
                        public int compare(CommentDto o1, CommentDto o2) {
                            return o2.getCreateTime().compareTo(o1.getCreateTime());
                        }
                    });
                    dto.setChildren(children);
                }
            });
            return root;
        }
        return buildTree(list, commentDtos, deep + 1);
    }


    @Override
    public PageData<CommentDto> getPage(PageParams pageParams, QueryParams queryParams) {
        CommentParams commentParams = (CommentParams) queryParams;
        Long page = pageParams.getPage();
        Long limit = pageParams.getLimit();
        List<CommentDto> list = commentDao.getPage((page - 1) * limit, limit, commentParams);
        Long count = commentDao.getCount(commentParams);
        return new PageData<>(list,count);
    }

    @Override
    public CommentDto saveVoByUser(CommentVo vo) {
        Comment comment = ConvertUtils.sourceToTarget(vo, Comment.class);
        // 敏感内容审核，并替换，通过则审核状态置为1
        // 之后用 MQ 来审核（好像检测替换性能足够，不需要了）
        String content = SensitiveWordUtils.replace(comment.getContent());
        comment.setContent(content);
        comment.setReview(1);
        // 设置评论人账号
        Integer userAuthId = null;
        try {
            userAuthId = SecurityUtils.getUserAuthId();
            comment.setUserAuthId(userAuthId);
            // 评论类型为用户
            comment.setType(UserTypeEums.User.getName());
        } catch (Exception e) {
        }
        String ipAddress = IPUtils.getIpAddress(request);
        String ipSource = IPUtils.getIpSource(ipAddress);
        comment.setIpAddress(ipAddress);
        comment.setIpSource(ipSource);
        String osName = UserAgentUtils.getOsName(request);
        String browserName = UserAgentUtils.getBrowserName(request);
        comment.setDevice(osName);
        comment.setBrowser(browserName);
        save(comment);
        // 获取该评论的详细信息
        CommentDto commentDto = commentDao.getComment(comment.getId());
        // 统一用 infoEmail 做邮箱展示
        if(StringUtils.isBlank(commentDto.getInfoEmail())){
            commentDto.setInfoEmail(commentDto.getEmail());
            commentDto.setEmail(null);
        }
        // 通知相关用户
        Integer parentCommentId=comment.getParentId();
        if(ObjectUtils.isNull(parentCommentId)||parentCommentId==0){
            parentCommentId=comment.getRootId();
        }
        String nickname=commentDto.getInfoName();
        if(StringUtils.isBlank(nickname)){
            nickname=commentDto.getNickname();
            // 统一用 infoName 作为用户昵称展示
            commentDto.setInfoEmail(nickname);
            commentDto.setNickname(null);
        }
        // 通知用户
        emailService.commentNotice(vo.getTopicType(), vo.getUrl(),parentCommentId,nickname ,comment.getContent(),ipAddress,ipSource,osName,browserName);
        // 返回该条评论的详细数据
        return commentDto;
    }
}


