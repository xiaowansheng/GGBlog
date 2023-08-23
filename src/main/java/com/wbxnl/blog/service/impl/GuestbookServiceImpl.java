package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.model.dto.GuestbookDto;
import com.wbxnl.blog.model.entity.Guestbook;
import com.wbxnl.blog.dao.GuestbookDao;
import com.wbxnl.blog.model.vo.GuestbookVo;
import com.wbxnl.blog.model.vo.params.GuestbookParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.QueryParams;
import com.wbxnl.blog.service.EmailService;
import com.wbxnl.blog.service.GuestbookService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import com.wbxnl.blog.utils.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 留言簿 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class GuestbookServiceImpl extends AbstractServiceImpl<GuestbookDao, Guestbook, GuestbookDto, GuestbookVo> implements GuestbookService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private EmailService emailService;

    @Override
    public PageData<GuestbookDto> getReviews(PageParams pageParams) {
        Long page = pageParams.getPage();
        Long limit = pageParams.getLimit();
        Page<Guestbook> guestbookPage = new Page<>(page, limit);
        // 获取未审核的内容
        LambdaQueryChainWrapper<Guestbook> chainWrapper = lambdaQuery()
                .eq(Guestbook::getReview, 0)
                .orderByDesc(Guestbook::getCreateTime);
        Page<Guestbook> selectPage = baseMapper.selectPage(guestbookPage, chainWrapper);
        return PageUtils.getPageData(selectPage, GuestbookDto.class);
    }

    @Override
    public void setQueryParamsByUser(QueryParams queryParams) {
        GuestbookParams guestbookParams = (GuestbookParams) queryParams;
        guestbookParams.setHidden(0);
        guestbookParams.setReview(1);
    }

    @Override
    public LambdaQueryWrapper<Guestbook> getQueryWrapper(QueryParams queryParams) {
        GuestbookParams guestbookParams = (GuestbookParams) queryParams;
        LambdaQueryWrapper<Guestbook> queryWrapper = new LambdaQueryWrapper<>();
        if(ObjectUtils.isNotNull(guestbookParams.getId())){
            queryWrapper.eq(Guestbook::getId,guestbookParams.getId());
        }
        if(ObjectUtils.isNotNull(guestbookParams.getUserAuthId())){
            queryWrapper.eq(Guestbook::getUserAuthId,guestbookParams.getUserAuthId());
        }
        if(StringUtils.isNotBlank(guestbookParams.getContent())){
            queryWrapper.like(Guestbook::getContent,guestbookParams.getContent());
        }
        if(StringUtils.isNotBlank(guestbookParams.getType())){
            queryWrapper.eq(Guestbook::getType,guestbookParams.getType());
        }
        if(StringUtils.isNotBlank(guestbookParams.getNickname())){
            queryWrapper.like(Guestbook::getNickname,guestbookParams.getNickname());
        }
        if(StringUtils.isNotBlank(guestbookParams.getEmail())){
            queryWrapper.like(Guestbook::getEmail,guestbookParams.getEmail());
        }
        if(StringUtils.isNotBlank(guestbookParams.getQq())){
            queryWrapper.like(Guestbook::getQq,guestbookParams.getQq());
        }
        if(ObjectUtils.isNotNull(guestbookParams.getHidden())){
            queryWrapper.eq(Guestbook::getHidden,guestbookParams.getHidden());
        }
        if(ObjectUtils.isNotNull(guestbookParams.getDevice())){
            queryWrapper.like(Guestbook::getDevice,guestbookParams.getDevice());
        }
        if(ObjectUtils.isNotNull(guestbookParams.getBrowser())){
            queryWrapper.like(Guestbook::getBrowser,guestbookParams.getBrowser());
        }
        if(StringUtils.isNotBlank(guestbookParams.getLocation())){
            queryWrapper.and(wrapper -> {
                wrapper.like(Guestbook::getLocation,guestbookParams.getLocation())
                        .or()
                        .like(Guestbook::getIpSource,guestbookParams.getLocation());
            });
        }
        if(ObjectUtils.isNotNull(guestbookParams.getBeginDate(),guestbookParams.getEndDate())){
            queryWrapper.between(Guestbook::getCreateTime,guestbookParams.getBeginDate(),guestbookParams.getEndDate());
        }
        queryWrapper.orderByDesc(Guestbook::getCreateTime);
        return queryWrapper;
    }

    @Override
    public Guestbook saveVo(GuestbookVo vo) {
        Guestbook guestbook = ConvertUtils.sourceToTarget(vo, Guestbook.class);
        try {
            guestbook.setUserAuthId(SecurityUtils.getUserAuthId());
        } catch (Exception e) {
            // 游客留言
        }
        // 敏感内容审核，替换
        guestbook.setContent(SensitiveWordUtils.replace(guestbook.getContent()));
        // 替换后，算是审核通过
        guestbook.setReview(1);
        String ipAddress = IPUtils.getIpAddress(request);
        String ipSource = IPUtils.getIpSource(ipAddress);
        guestbook.setIpAddress(ipAddress);
        guestbook.setIpSource(ipSource);
        String osName = UserAgentUtils.getOsName(request);
        String browserName = UserAgentUtils.getBrowserName(request);
        guestbook.setDevice(osName);
        guestbook.setBrowser(browserName);
        save(guestbook);
        // 新留言通知作者
        emailService.leaveWordNotice(guestbook);
        return guestbook;
    }
}
