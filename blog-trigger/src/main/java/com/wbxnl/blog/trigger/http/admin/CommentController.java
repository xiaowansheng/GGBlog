package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.CommentQueryReq;
import com.wbxnl.blog.api.admin.model.res.CommentDetailRes;
import com.wbxnl.blog.api.admin.service.ICommentService;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/6 11:03
 */
@Slf4j
@RestController
@RequestMapping("/admin/comment")
@RequiredArgsConstructor
public class CommentController implements ICommentService {

    @Override
    public PageData<CommentDetailRes> getPageOfComments(PageParams pageParams, CommentQueryReq commentQueryReq) {
        return null;
    }

    @Override
    public void updateCommentAuditStatus(Integer id, Integer auditStatus) {

    }

    @Override
    public void updateCommentTop(Integer id, Integer top) {

    }

    @Override
    public void updateCommentShowStatus(Integer id, Integer status) {

    }

    @Override
    public void deleteComment(Integer id) {

    }

    @Override
    public void deleteComment(Integer[] ids) {

    }
}
