package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wbxnl.blog.enums.ContentStateEums;
import com.wbxnl.blog.enums.TopicTypeEums;
import com.wbxnl.blog.enums.ViewTypeEums;
import com.wbxnl.blog.model.dto.TalkDto;
import com.wbxnl.blog.model.entity.Talk;
import com.wbxnl.blog.dao.TalkDao;
import com.wbxnl.blog.model.vo.TalkVo;
import com.wbxnl.blog.model.vo.params.QueryParams;
import com.wbxnl.blog.model.vo.params.TalkParams;
import com.wbxnl.blog.service.CommentService;
import com.wbxnl.blog.service.PageViewService;
import com.wbxnl.blog.service.TalkService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import com.wbxnl.blog.utils.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 说说 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
@Slf4j
public class TalkServiceImpl extends AbstractServiceImpl<TalkDao, Talk, TalkDto, TalkVo> implements TalkService {

    @Autowired
    private PageViewService pageViewService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private HttpServletRequest request;

    @Override
    public TalkDto getTalkByUser(Integer id) {
        // 查找符合id，并且内容公开的说说
        Talk talk = lambdaQuery().eq(Talk::getId, id).eq(Talk::getStatus, ContentStateEums.PUBLIC.getName()).one();
        TalkDto talkDto = ConvertUtils.sourceToTarget(talk, TalkDto.class);
        // 说说的访问量增加
        if(ObjectUtils.isNotNull(talkDto)){
            Long pageView = pageViewService.increasePageView(ViewTypeEums.TALK.getName(), id);
            // 响应结果中添加访问量
            talkDto.setPageView(pageView);
            // 添加评论数
            Long count = commentService.getCountByUser(TopicTypeEums.Talk.getName(), id);
            talkDto.setCommentCount(count);
        }
        return talkDto;
    }

    @Override
    public Long getCountByUser() {
        return lambdaQuery().eq(Talk::getStatus,ContentStateEums.PUBLIC.getName()).count();
    }


    @Override
    public void setQueryParamsByUser(QueryParams queryParams) {
        TalkParams talkParams = (TalkParams) queryParams;
        talkParams.setStatus(ContentStateEums.PUBLIC.getName());
    }

    @Override
    public LambdaQueryWrapper<Talk> getQueryWrapper(QueryParams queryParams) {
        TalkParams talkParams = (TalkParams) queryParams;
        LambdaQueryWrapper<Talk> queryWrapper = new LambdaQueryWrapper<>();
        if(ObjectUtils.isNotNull(talkParams.getId())){
            queryWrapper.eq(Talk::getId,talkParams.getId());
        }
        if(StringUtils.isNotBlank(talkParams.getContent())){
            queryWrapper.like(Talk::getContent,talkParams.getContent());
        }
        if(StringUtils.isNotBlank(talkParams.getStatus())){
            queryWrapper.eq(Talk::getStatus,talkParams.getStatus());
        }
        if(ObjectUtils.isNotNull(talkParams.getTop())){
            queryWrapper.eq(Talk::getTop,talkParams.getTop());
        }
        if(StringUtils.isNotBlank(talkParams.getDevice())){
            queryWrapper.like(Talk::getDevice,talkParams.getDevice());
        }
        if(StringUtils.isNotBlank(talkParams.getBrowser())){
            queryWrapper.like(Talk::getBrowser,talkParams.getBrowser());
        }
        // 开始时间和结束时间不空时，筛选时间在两个时间区间
        // 需要保证开始时间小于结束时间
        if(ObjectUtils.isNotNull(talkParams.getBeginDate(),talkParams.getEndDate())){
            queryWrapper.between(Talk::getCreateTime,talkParams.getBeginDate(),talkParams.getEndDate());
        }
        // 默认按置顶和时间排序
        return queryWrapper
                .orderByDesc(Talk::getTop)
                .orderByDesc(Talk::getCreateTime);
    }

    @Override
    public Talk saveVo(TalkVo vo) {
        Talk talk = ConvertUtils.sourceToTarget(vo, Talk.class);
        talk.setUserAuthId(SecurityUtils.getUserAuthId());
        String ipAddress = IPUtils.getIpAddress(request);
        String ipSource = IPUtils.getIpSource(ipAddress);
        talk.setIpAddress(ipAddress);
        talk.setIpSource(ipSource);
        String osName = UserAgentUtils.getOsName(request);
        String browserName = UserAgentUtils.getBrowserName(request);
        talk.setDevice(osName);
        talk.setBrowser(browserName);
        save(talk);
        return talk;
    }

}
