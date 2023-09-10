package com.wbxnl.blog.dao;

import com.wbxnl.blog.model.dto.extra.NameValueDto;
import com.wbxnl.blog.model.entity.Talk;
import com.wbxnl.blog.dao.base.BaseDao;
import com.wbxnl.blog.model.vo.params.DateIntervalParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 根据时间统计查询说说数量
     * @param dateIntervalParams
     * @return
     */
    List<NameValueDto> getStatisticsOfCount(@Param("queryParams") DateIntervalParams dateIntervalParams);
}
