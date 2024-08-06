package com.wbxnl.blog.trigger.http.front;

import com.wbxnl.blog.api.front.model.req.CommentDataReq;
import com.wbxnl.blog.api.front.model.res.CommentInfoRes;
import com.wbxnl.blog.api.front.service.ICommentService;
import com.wbxnl.blog.common.vo.KeyData;
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
 * @since 2024/8/6 11:31
 */
@Slf4j
@RestController
@RequestMapping("/front/comment")
@RequiredArgsConstructor
public class CommentController implements ICommentService {

    @Override
    public KeyData addComment(String topicType, String topicKey, CommentDataReq commentDataReq) {
        return null;
    }

    @Override
    public PageData<CommentInfoRes> getPageOfComments(PageParams pageParams, String topicType, String topicKey) {
        return null;
    }
}
