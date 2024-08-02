package com.wbxnl.blog.api.front.service;

import com.wbxnl.blog.api.front.model.res.TalkInfoRes;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/2 14:47
 */
public interface ITalkService {
    /**
     * 获取分页说说数据
     * @param pageParams 分页参数
     * @return 说说数据
     */
    PageData<TalkInfoRes> getPageOfTalks(PageParams pageParams);

    /**
     * 获取说说详情
     * @param id 说说id
     * @return 说说详情
     */
    TalkInfoRes getTalkInfo(Integer id);
}
