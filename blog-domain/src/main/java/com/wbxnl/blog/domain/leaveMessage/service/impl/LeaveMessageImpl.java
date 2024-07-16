package com.wbxnl.blog.domain.leaveMessage.service.impl;

import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageEntity;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageQueryEntity;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageSimpleEntity;
import com.wbxnl.blog.domain.leaveMessage.model.vo.LeaveMessageVo;
import com.wbxnl.blog.domain.leaveMessage.repository.ILeaveMessageRepository;
import com.wbxnl.blog.domain.leaveMessage.service.ILeaveMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/17 0:13
 */
@Service
@RequiredArgsConstructor
public class LeaveMessageImpl implements ILeaveMessage {

    private final ILeaveMessageRepository leaveMessageRepository;

    @Override
    public LeaveMessageEntity addLeaveMessage(LeaveMessageVo leaveMessageVo) {
        return leaveMessageRepository.addLeaveMessage(leaveMessageVo);
    }

    @Override
    public boolean deleteLeaveMessage(Integer id) {
        return leaveMessageRepository.deleteLeaveMessage(id);
    }

    @Override
    public boolean deleteLeaveMessage(Integer[] ids) {
        return leaveMessageRepository.deleteLeaveMessage(ids);
    }

    @Override
    public boolean auditLeaveMessage(Integer id, Integer review) {
        return leaveMessageRepository.auditLeaveMessage(id, review);
    }

    @Override
    public boolean updateLeaveMessageHidden(Integer id, Integer hidden) {
        return leaveMessageRepository.updateLeaveMessageHidden(id, hidden);
    }

    @Override
    public PageData<LeaveMessageEntity> getPageLeaveMessages(PageParams pageParams, LeaveMessageQueryEntity leaveMessageQueryEntity) {
        return leaveMessageRepository.getPageLeaveMessages(pageParams, leaveMessageQueryEntity);
    }

    @Override
    public PageData<LeaveMessageEntity> getPageOfNoAuditLeaveMessages(PageParams pageParams, LeaveMessageQueryEntity leaveMessageQueryEntity) {
        return leaveMessageRepository.getPageOfNoAuditLeaveMessages(pageParams, leaveMessageQueryEntity);
    }

    @Override
    public PageData<LeaveMessageSimpleEntity> getPageLeaveMessagesByUser(PageParams pageParams) {
        return leaveMessageRepository.getPageLeaveMessagesByUser(pageParams);
    }
}
