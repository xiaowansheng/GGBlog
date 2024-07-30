package com.wbxnl.blog.domain.talk.service;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.talk.model.aggregate.TalkAggregate;
import com.wbxnl.blog.domain.talk.model.entity.TalkEntity;
import com.wbxnl.blog.domain.talk.model.entity.TalkQueryEntity;
import com.wbxnl.blog.domain.talk.model.entity.TalkUpdateEntity;
import com.wbxnl.blog.domain.talk.model.vo.TalkVo;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/15 20:56
 */
public interface ITalkService {

    /**
     * 添加说说
     * @param talkVo 说说内容
     * @return 添加后的说说
     */
    TalkEntity addTalk(TalkVo talkVo);

    /**
     * 删除说说
     * @param id 说说id
     * @return 是否删除成功
     */
    boolean deleteTalk(Integer id);

    /**
     * 批量删除说说
     * @param ids 说说id
     * @return 是否删除成功
     */
    boolean deleteTalk(Integer[] ids);

    /**
     * 更新说说
     * @param talkUpdateEntity 更新的内容
     * @return 是否更新成功
     */
    boolean updateTalk(TalkUpdateEntity talkUpdateEntity);

    /**
     * 更新说说状态
     * @param id 说说id
     * @param status 说说状态
     * @return 是否更新成功
     */
    boolean updateTalkStatus(Integer id, String status);

    /**
     * 更新说说置顶
     * @param id 说说id
     * @param top 说说置顶
     * @return 是否更新成功
     */
    boolean updateTalkTop(Integer id, Integer top);

    /**
     * 获取说说
     * @param id 说说id
     * @return 说说
     */
    TalkEntity getTalk(Integer id);

    /**
     * 获取说说详情
     * @param id 说说id
     * @param isVisitor 是否为访客
     * @return 说说
     */
    TalkAggregate getTalkDetail(Integer id, boolean isVisitor);

    /**
     * 获取说说列表
     * @param pageParams 分页参数
     * @param talkQueryEntity 查询参数
     * @return 说说列表
     */
    PageData<TalkAggregate> getPageTalkDetails(PageParams pageParams, TalkQueryEntity talkQueryEntity);

    /**
     * 游客获取说说列表
     * @param pageParams 分页参数
     * @return 说说列表
     */
    PageData<TalkAggregate> getPageTalkDetailsByUser(PageParams pageParams);
}
