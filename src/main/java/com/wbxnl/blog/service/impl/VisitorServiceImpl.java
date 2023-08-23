package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wbxnl.blog.model.dto.VisitorDto;
import com.wbxnl.blog.model.entity.Visitor;
import com.wbxnl.blog.dao.VisitorDao;
import com.wbxnl.blog.model.vo.params.QueryParams;
import com.wbxnl.blog.model.vo.params.VisitorParams;
import com.wbxnl.blog.service.VisitorService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 访客信息 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class VisitorServiceImpl extends AbstractServiceImpl<VisitorDao, Visitor, VisitorDto, Visitor> implements VisitorService {

    @Override
    public LambdaQueryWrapper<Visitor> getQueryWrapper(QueryParams queryParams) {
        VisitorParams visitorParams = (VisitorParams) queryParams;
        LambdaQueryWrapper<Visitor> queryWrapper = new LambdaQueryWrapper<>();
        if(ObjectUtils.isNotNull(visitorParams.getId())){
            queryWrapper.eq(Visitor::getId,visitorParams.getId());
        }
        if(StringUtils.isNotBlank(visitorParams.getUuid())){
            queryWrapper.eq(Visitor::getUuid,visitorParams.getUuid());
        }
        if(StringUtils.isNotBlank(visitorParams.getViewType())){
            queryWrapper.eq(Visitor::getViewType,visitorParams.getViewType());
            if(ObjectUtils.isNotNull(visitorParams.getViewId())){
                queryWrapper.eq(Visitor::getViewId,visitorParams.getViewId());
            }
        }
        if(StringUtils.isNotBlank(visitorParams.getDevice())){
            queryWrapper.like(Visitor::getDevice,visitorParams.getDevice());
        }
        if(StringUtils.isNotBlank(visitorParams.getBrowser())){
            queryWrapper.like(Visitor::getBrowser,visitorParams.getBrowser());
        }
        if(StringUtils.isNotBlank(visitorParams.getLocation())){
            queryWrapper.and(wrapper->{
                wrapper
                        .like(Visitor::getIpSource,visitorParams.getLocation())
                        .or()
                        .like(Visitor::getLocation,visitorParams.getLocation());
            });
        }
        if(ObjectUtils.isNotNull(visitorParams.getBeginDate(),visitorParams.getEndDate())){
            queryWrapper.between(Visitor::getCreateTime,visitorParams.getBeginDate(),visitorParams.getEndDate());
        }
        queryWrapper.orderByDesc(Visitor::getCreateTime);
        return queryWrapper;
    }
}
