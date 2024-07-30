package com.wbxnl.blog.domain.pageView.service.impl;

import com.wbxnl.blog.common.utils.HttpUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.pageView.model.entity.PageViewEntity;
import com.wbxnl.blog.domain.pageView.model.entity.VisitorEntity;
import com.wbxnl.blog.domain.pageView.model.entity.VisitorQueryEntity;
import com.wbxnl.blog.domain.pageView.model.vo.PageViewVo;
import com.wbxnl.blog.domain.pageView.model.vo.VisitorVo;
import com.wbxnl.blog.domain.pageView.repository.IPageViewRepository;
import com.wbxnl.blog.domain.pageView.service.IPageViewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 11:17
 */
@Service
@RequiredArgsConstructor
public class PageViewService implements IPageViewService {

    private final IPageViewRepository pageViewRepository;

    private final HttpServletRequest request;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageViewEntity addPageView(PageViewVo pageViewVo) {
        String ipAddress = HttpUtils.getIpAddress(request);
        VisitorVo visitorVo = VisitorVo.builder()
                .uuid("")
                .viewType(pageViewVo.getViewType())
                .viewKey(pageViewVo.getViewKey())
                .ipAddress(ipAddress)
                .ipSource(HttpUtils.getIpSource(ipAddress))
                .device(HttpUtils.getRequestDevice(request))
                .browser(HttpUtils.getRequestBrowser(request))
                .build();
        addVisitor(visitorVo);
        return pageViewRepository.addPageView(pageViewVo);
    }

    /**
     * 添加访客
     * @param visitorVo 访客信息
     * @return 访客信息
     */
    private VisitorEntity addVisitor(VisitorVo visitorVo){
        return pageViewRepository.addVisitor(visitorVo);
    }

    @Override
    public PageViewEntity getPageView(String viewType, String viewKey) {
        return pageViewRepository.getPageView(viewType, viewKey);
    }

    @Override
    public PageData<VisitorEntity> getPageVisitors(PageParams pageParams, VisitorQueryEntity visitorQueryEntity) {
        return pageViewRepository.getPageVisitors(pageParams, visitorQueryEntity);
    }
}
