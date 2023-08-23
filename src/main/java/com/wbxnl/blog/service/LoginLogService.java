package com.wbxnl.blog.service;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.model.dto.LoginLogDto;
import com.wbxnl.blog.model.entity.LoginLog;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.service.base.BaseService;


/**
 * <p>
 * 登录日志，记录用户的登录信息 服务类
 * </p>
 *
 * @author wansheng
 * @since 2023-08-07
 */
public interface LoginLogService extends BaseService<LoginLog, LoginLogDto, LoginLog> {

    /**
     * 用户查询自己的登录日志
     * @param pageParams
     * @return
     */
    PageData<LoginLogDto> getLoginLogByUser(PageParams pageParams);

    /**
     * 根据用户编号，查询登录日志
     * @param pageParams
     * @param userAuthId
     * @return
     */
    PageData<LoginLogDto> getLoginLog(PageParams pageParams, Integer userAuthId);
}
