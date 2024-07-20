package com.wbxnl.blog.domain.talk.repository;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.talk.model.aggregate.TalkAggregate;
import com.wbxnl.blog.domain.talk.model.entity.TalkEntity;
import com.wbxnl.blog.domain.talk.model.entity.TalkQueryEntity;
import com.wbxnl.blog.domain.talk.model.entity.TalkUpdateEntity;
import com.wbxnl.blog.domain.talk.model.vo.TalkInsertVo;

/**
 * @description:
 * @author: xiaowansheng
 * @date: 2024/7/16 16:22
 */
public interface ITalkRepository {

    TalkEntity addTalk(TalkInsertVo talkInsertVo);

    boolean deleteTalk(Integer id);

    boolean deleteTalk(Integer[] ids);

    boolean updateTalk(TalkUpdateEntity talkUpdateEntity);

    boolean updateTalkStatus(Integer id, String status);

    boolean updateTalkTop(Integer id, Integer top);

    PageData<TalkAggregate> getPageTalkDetails(PageParams pageParams, TalkQueryEntity talkQueryEntity);

    PageData<TalkAggregate> getPageTalkDetailsOfVisitor(PageParams pageParams);

    TalkEntity getTalk(Integer id);

    TalkAggregate getTalkDetail(Integer id, boolean isVisitor);

}
