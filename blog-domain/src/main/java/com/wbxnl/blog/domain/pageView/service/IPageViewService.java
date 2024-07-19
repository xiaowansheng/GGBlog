package com.wbxnl.blog.domain.pageView.service;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.pageView.model.entity.PageViewEntity;
import com.wbxnl.blog.domain.pageView.model.entity.VisitorEntity;
import com.wbxnl.blog.domain.pageView.model.entity.VisitorQueryEntity;
import com.wbxnl.blog.domain.pageView.model.vo.PageViewVo;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/19 11:04
 */
public interface IPageViewService {
    /**
     * 添加页面浏览量
     * @param pageViewVo 浏览记录
     * @return 返回浏览记录信息
     */
    PageViewEntity addPageView(PageViewVo pageViewVo);

    /**
     * 获取页面浏览量
     * @param viewType 视图类型
     * @param viewId 视图id
     * @return 访问量信息
     */
    PageViewEntity getPageView(String viewType, Integer viewId);

    /**
     * 获取页面访问记录
     * @param pageParams 分页参数
     * @param visitorQueryEntity 查询参数
     * @return 访问记录
     */
    PageData<VisitorEntity> getPageVisitors(PageParams pageParams, VisitorQueryEntity visitorQueryEntity);
}
