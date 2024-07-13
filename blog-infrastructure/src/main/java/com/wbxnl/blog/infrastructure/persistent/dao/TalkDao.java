package com.wbxnl.blog.infrastructure.persistent.dao;

import com.wbxnl.blog.infrastructure.persistent.dao.base.BaseDao;
import com.wbxnl.blog.infrastructure.persistent.po.Talk;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 说说 Mapper 接口
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Mapper
public interface TalkDao extends BaseDao<Talk> {

//    /**
//     * 根据时间统计查询说说数量
//     * @param dateIntervalParams
//     * @return
//     */
//    List<NameValueDto> getStatisticsOfCount(@Param("queryParams") DateIntervalParams dateIntervalParams);
}
