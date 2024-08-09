package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.TalkDataReq;
import com.wbxnl.blog.api.admin.model.req.TalkQueryReq;
import com.wbxnl.blog.api.admin.model.res.TalkDetailRes;
import com.wbxnl.blog.api.admin.service.ITalkService;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.KeyData;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.talk.model.aggregate.TalkAggregate;
import com.wbxnl.blog.domain.talk.model.entity.TalkEntity;
import com.wbxnl.blog.domain.talk.model.entity.TalkQueryEntity;
import com.wbxnl.blog.domain.talk.model.entity.TalkUpdateEntity;
import com.wbxnl.blog.domain.talk.model.vo.TalkVo;
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
        TalkVo talkVo = ObjectConvertUtils.convert(talkDataReq, TalkVo.class);
        TalkEntity talkEntity = talkService.addTalk(talkVo);
        return KeyData
                .builder()
                .id(talkEntity.getId())
                .key(talkEntity.getTalkKey())
                .build();
    }

    @Override
    public void updateTalk(TalkDataReq talkDataReq) {
        TalkUpdateEntity talkUpdateEntity = ObjectConvertUtils.convert(talkDataReq, TalkUpdateEntity.class);
        talkService.updateTalk(talkUpdateEntity);
    }

    @Override
    public void updateTalkTop(Integer id, Integer top) {
        talkService.updateTalkTop(id, top);
    }

    @Override
    public void updateTalkStatus(Integer id, String status) {
        talkService.updateTalkStatus(id, status);
    }

    @Override
    public void deleteTalk(Integer id) {
        talkService.deleteTalk(id);
    }

    @Override
    public void deleteTalk(Integer[] ids) {
        talkService.deleteTalk(ids);
    }

    @Override
    public PageData<TalkDetailRes> getPageOfTalks(PageParams pageParams, TalkQueryReq talkQueryReq) {
        TalkQueryEntity talkQueryEntity = ObjectConvertUtils.convert(talkQueryReq, TalkQueryEntity.class);
        PageData<TalkAggregate> pageTalkDetails = talkService.getPageTalkDetails(pageParams, talkQueryEntity);
        return PageData
                .<TalkDetailRes>builder()
                .data(ObjectConvertUtils.convertList(pageTalkDetails.getData(), TalkDetailRes.class))
                .number(pageTalkDetails.getNumber())
                .size(pageTalkDetails.getSize())
                .total(pageTalkDetails.getTotal())
                .build();
    }
}
