package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.TalkDataReq;
import com.wbxnl.blog.api.admin.model.req.TalkQueryReq;
import com.wbxnl.blog.api.admin.model.res.TalkDetailRes;
import com.wbxnl.blog.api.admin.service.ITalkService;
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
 * @since 2024/8/6 11:20
 */
@Slf4j
@RestController
@RequestMapping("/admin/talk")
@RequiredArgsConstructor
public class TalkController implements ITalkService {

    private final com.wbxnl.blog.domain.talk.service.ITalkService talkService;

    @Override
    public KeyData publishTalk(TalkDataReq talkDataReq) {
        return null;
    }

    @Override
    public void updateTalk(TalkDataReq talkDataReq) {

    }

    @Override
    public void updateTalkTop(Integer id, Integer top) {

    }

    @Override
    public void updateTalkStatus(Integer id, String status) {

    }

    @Override
    public void deleteTalk(Integer id) {

    }

    @Override
    public void deleteTalk(Integer[] ids) {

    }

    @Override
    public PageData<TalkDetailRes> getPageOfTalks(PageParams pageParams, TalkQueryReq talkQueryReq) {
        return null;
    }
}
