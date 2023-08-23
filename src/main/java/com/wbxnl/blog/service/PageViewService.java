package com.wbxnl.blog.service;

import com.wbxnl.blog.model.dto.PageViewDto;
import com.wbxnl.blog.model.entity.PageView;
import com.wbxnl.blog.service.base.BaseService;

/**
 * <p>
 * 访问量 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface PageViewService extends BaseService<PageView, PageViewDto, PageView> {
    /**
     * 页指定的面访问量+1
     * @param viewType
     * @param viewId
     * @return 返回当前最新的访问量
     */
    Long increasePageView(String viewType, Integer viewId);
}
