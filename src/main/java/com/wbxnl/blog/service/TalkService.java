package com.wbxnl.blog.service;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.model.dto.TalkDto;
import com.wbxnl.blog.model.dto.extra.NameValueDto;
import com.wbxnl.blog.model.entity.Talk;
import com.wbxnl.blog.model.vo.TalkVo;
import com.wbxnl.blog.model.vo.params.DateIntervalParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.TalkParams;
import com.wbxnl.blog.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 说说 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface TalkService extends BaseService< Talk, TalkDto, TalkVo> {
    /**
     * 用户根据ID查询说说内容
     *  并且添加说说的用户浏览信息
     * @param id
     * @return
     */

    TalkDto getTalkByUser(Integer id);

    /**
     * 用户获取说说总数
     * @return
     */
    public Long getCountByUser();

    /**
     * 根据时间统计查询说说数量
     * @param dateIntervalParams
     * @return
     */
    List<NameValueDto> getStatisticsOfCount(DateIntervalParams dateIntervalParams);
}
