package com.wbxnl.blog.service;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.model.dto.GuestbookDto;
import com.wbxnl.blog.model.entity.Guestbook;
import com.wbxnl.blog.model.vo.GuestbookVo;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 留言簿 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface GuestbookService extends BaseService< Guestbook, GuestbookDto, GuestbookVo> {
    /**
     * 查询需要审核的留言
     * @param pageParams
     * @return
     */
    PageData<GuestbookDto> getReviews(PageParams pageParams);
}
