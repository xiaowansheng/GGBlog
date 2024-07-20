package com.wbxnl.blog.domain.leaveMessage.repository;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageEntity;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageQueryEntity;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageSimpleEntity;
import com.wbxnl.blog.domain.leaveMessage.model.vo.LeaveMessageInsertVo;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 0:14
 */
public interface ILeaveMessageRepository {

    LeaveMessageEntity addLeaveMessage(LeaveMessageInsertVo leaveMessageInsertVo);

    boolean deleteLeaveMessage(Integer id);

    boolean deleteLeaveMessage(Integer[] ids);

    boolean auditLeaveMessage(Integer id, Integer review);

    boolean updateLeaveMessageHidden(Integer id, Integer hidden);

    PageData<LeaveMessageEntity> getPageLeaveMessages(PageParams pageParams, LeaveMessageQueryEntity leaveMessageQueryEntity);

    PageData<LeaveMessageEntity> getPageOfNoAuditLeaveMessages(PageParams pageParams, LeaveMessageQueryEntity leaveMessageQueryEntity);

    PageData<LeaveMessageSimpleEntity> getPageLeaveMessagesByUser(PageParams pageParams);
}
