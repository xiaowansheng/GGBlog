package com.wbxnl.blog.api.admin.service;

import com.wbxnl.blog.api.admin.model.req.LeaveWordQueryReq;
import com.wbxnl.blog.api.admin.model.res.LeaveWordDetailRes;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/4 17:30
 */
public interface ILeaveWordService {

    /**
     * 分页查询留言
     * @param pageParams 分页参数
     * @param leaveWordQueryReq 查询参数
     * @return 分页数据
     */
    PageData<LeaveWordDetailRes> getPageOfLeaveWords(PageParams pageParams, LeaveWordQueryReq leaveWordQueryReq);

    /**
     * 更新留言状态
     * @param id 留言id
     * @param status 状态
     */
    void updateLeaveWordShowStatus(Integer id, Integer status);

    /**
     * 更新留言审核状态
     * @param id 留言id
     * @param auditStatus 审核状态
     */
    void updateLeaveWordAuditStatus(Integer id, Integer auditStatus);

    /**
     * 删除留言
     * @param id 留言id
     */
    void deleteLeaveWord(Integer id);

    /**
     * 批量删除留言
     * @param ids 留言id
     */
    void deleteLeaveWord(Integer[] ids);
}
