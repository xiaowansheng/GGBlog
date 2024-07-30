package com.wbxnl.blog.domain.pageView.repository;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.pageView.model.entity.PageViewEntity;
import com.wbxnl.blog.domain.pageView.model.entity.VisitorEntity;
import com.wbxnl.blog.domain.pageView.model.entity.VisitorQueryEntity;
import com.wbxnl.blog.domain.pageView.model.vo.PageViewVo;
import com.wbxnl.blog.domain.pageView.model.vo.VisitorVo;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 14:19
 */
public interface IPageViewRepository {


    PageViewEntity addPageView(PageViewVo pageViewVo);

    VisitorEntity addVisitor(VisitorVo visitorVo);

    PageViewEntity getPageView(String viewType, String viewKey);

    PageData<VisitorEntity> getPageVisitors(PageParams pageParams, VisitorQueryEntity visitorQueryEntity);
}
