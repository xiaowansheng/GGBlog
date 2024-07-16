package com.wbxnl.blog.domain.leaveMessage.service;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageEntity;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageQueryEntity;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageSimpleEntity;
import com.wbxnl.blog.domain.leaveMessage.model.vo.LeaveMessageVo;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 0:03
 */
public interface ILeaveMessage {
    /**
     * 添加留言
     * @param leaveMessageVo 留言信息
     * @return 添加结果
     */
    LeaveMessageEntity addLeaveMessage(LeaveMessageVo leaveMessageVo);

    /**
     * 删除留言
     * @param id 留言id
     * @return 删除结果
     */
    boolean deleteLeaveMessage(Integer id);

    /**
     * 批量删除留言
     * @param ids 留言
     * @return 删除结果
     */
    boolean deleteLeaveMessage(Integer[] ids);

    /**
     * 审核留言
     * @param id 留言
     * @param review 是否通过
     * @return 是否通过
     */
    boolean auditLeaveMessage(Integer id, Integer review);

    /**
     * 修改留言隐藏状态
     * @param id 留言id
     * @param hidden 是否隐藏
     * @return 是否隐藏
     */
    boolean updateLeaveMessageHidden(Integer id, Integer hidden);

    /**
     * 分页查询留言
     * @param pageParams 分页参数
     * @param leaveMessageQueryEntity 查询条件
     * @return 留言
     */
    PageData<LeaveMessageEntity> getPageLeaveMessages(PageParams pageParams, LeaveMessageQueryEntity leaveMessageQueryEntity);

    /**
     * 分页查询未审核的留言
     * @param pageParams 分页参数
     * @param leaveMessageQueryEntity 查询条件
     * @return 留言
     */
    PageData<LeaveMessageEntity> getPageOfNoAuditLeaveMessages(PageParams pageParams, LeaveMessageQueryEntity leaveMessageQueryEntity);

    /**
     * 用户获取留言
     * @param pageParams 分页参数
     * @return 留言
     */
    PageData<LeaveMessageSimpleEntity> getPageLeaveMessagesByUser(PageParams pageParams);
}
