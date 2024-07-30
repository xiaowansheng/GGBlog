package com.wbxnl.blog.infrastructure.persistent.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.pageView.model.entity.PageViewEntity;
import com.wbxnl.blog.domain.pageView.model.entity.VisitorEntity;
import com.wbxnl.blog.domain.pageView.model.entity.VisitorQueryEntity;
import com.wbxnl.blog.domain.pageView.model.vo.PageViewVo;
import com.wbxnl.blog.domain.pageView.model.vo.VisitorVo;
import com.wbxnl.blog.domain.pageView.repository.IPageViewRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.PageViewDao;
import com.wbxnl.blog.infrastructure.persistent.dao.VisitorDao;
import com.wbxnl.blog.infrastructure.persistent.po.PageView;
import com.wbxnl.blog.infrastructure.persistent.po.Visitor;
import com.wbxnl.blog.infrastructure.persistent.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/30 16:29
 */
@Service
@RequiredArgsConstructor
public class PageViewRepository implements IPageViewRepository {

    private final PageViewDao pageViewDao;

    private final VisitorDao visitorDao;

    @Override
    public PageViewEntity addPageView(PageViewVo pageViewVo) {
        PageView pageView = ObjectConvertUtils.convert(pageViewVo, PageView.class);
        pageViewDao.insert(pageView);
        return ObjectConvertUtils.convert(pageView, PageViewEntity.class);
    }

    @Override
    public VisitorEntity addVisitor(VisitorVo visitorVo) {
        Visitor visitor = ObjectConvertUtils.convert(visitorVo, Visitor.class);
        visitorDao.insert(visitor);
        return ObjectConvertUtils.convert(visitor, VisitorEntity.class);
    }

    @Override
    public PageViewEntity getPageView(String viewType, String viewKey) {
        LambdaQueryWrapper<PageView> pageViewLambdaQueryWrapper = new LambdaQueryWrapper<>();
        pageViewLambdaQueryWrapper
                .eq(PageView::getViewType, viewType)
                .eq(PageView::getViewKey, viewKey);
        PageView pageView = pageViewDao.selectOne(pageViewLambdaQueryWrapper);
        return ObjectConvertUtils.convert(pageView, PageViewEntity.class);
    }

    @Override
    public PageData<VisitorEntity> getPageVisitors(PageParams pageParams, VisitorQueryEntity visitorQueryEntity) {
        Page<Visitor> page = new Page<>(pageParams.getNumber(), pageParams.getSize());
        LambdaQueryWrapper<Visitor> visitorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        visitorLambdaQueryWrapper
                .eq(StringUtils.isNotBlank(visitorQueryEntity.getUuid()), Visitor::getUuid, visitorQueryEntity.getUuid())
                .eq(StringUtils.isNotBlank(visitorQueryEntity.getViewType()), Visitor::getViewType, visitorQueryEntity.getViewType())
                .eq(StringUtils.isNotBlank(visitorQueryEntity.getViewKey()), Visitor::getViewKey, visitorQueryEntity.getViewKey())
                .eq(StringUtils.isNotBlank(visitorQueryEntity.getIpAddress()), Visitor::getIpAddress, visitorQueryEntity.getIpAddress())
                .eq(StringUtils.isNotBlank(visitorQueryEntity.getIpSource()), Visitor::getIpSource, visitorQueryEntity.getIpSource())
                .eq(StringUtils.isNotBlank(visitorQueryEntity.getDevice()), Visitor::getDevice, visitorQueryEntity.getDevice())
                .eq(StringUtils.isNotBlank(visitorQueryEntity.getBrowser()), Visitor::getBrowser, visitorQueryEntity.getBrowser())
                .between(!ObjectUtils.isEmpty(visitorQueryEntity.getBeginCreateTime()) && !ObjectUtils.isEmpty(visitorQueryEntity.getEndCreateTime()), Visitor::getCreateTime, visitorQueryEntity.getBeginCreateTime(), visitorQueryEntity.getEndCreateTime())
                .orderByDesc(Visitor::getCreateTime);
        Page<Visitor> visitorPage = visitorDao.selectPage(page, visitorLambdaQueryWrapper);
        List<VisitorEntity> visitorEntities = ObjectConvertUtils.convertList(visitorPage.getRecords(), VisitorEntity.class);
        return PageUtils.convertPageData(pageParams.getNumber(),pageParams.getSize(),visitorPage.getTotal(),visitorEntities);
    }
}
