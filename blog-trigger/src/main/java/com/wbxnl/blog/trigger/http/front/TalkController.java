package com.wbxnl.blog.trigger.http.front;

import com.wbxnl.blog.api.front.model.res.TalkInfoRes;
import com.wbxnl.blog.api.front.service.ITalkService;
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
 * @since 2024/8/6 11:34
 */
@Slf4j
@RestController
@RequestMapping("/front/talk")
@RequiredArgsConstructor
public class TalkController implements ITalkService {

    private final com.wbxnl.blog.domain.talk.service.ITalkService talkService;

    @Override
    public PageData<TalkInfoRes> getPageOfTalks(PageParams pageParams) {
        return null;
    }

    @Override
    public TalkInfoRes getTalkInfo(Integer id) {
        return null;
    }
}
