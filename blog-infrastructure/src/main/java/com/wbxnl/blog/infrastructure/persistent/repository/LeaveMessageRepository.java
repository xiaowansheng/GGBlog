package com.wbxnl.blog.infrastructure.persistent.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageEntity;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageQueryEntity;
import com.wbxnl.blog.domain.leaveMessage.model.entity.LeaveMessageSimpleEntity;
import com.wbxnl.blog.domain.leaveMessage.model.vo.LeaveMessageInsertVo;
import com.wbxnl.blog.domain.leaveMessage.repository.ILeaveMessageRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.GuestbookDao;
import com.wbxnl.blog.infrastructure.persistent.po.Guestbook;
import com.wbxnl.blog.infrastructure.persistent.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/31 16:06
 */
@Service
@RequiredArgsConstructor
public class LeaveMessageRepository implements ILeaveMessageRepository {

    private final GuestbookDao guestbookDao;

    @Override
    public LeaveMessageEntity addLeaveMessage(LeaveMessageInsertVo leaveMessageInsertVo) {
        Guestbook guestbook = ObjectConvertUtils.convert(leaveMessageInsertVo, Guestbook.class);
        guestbookDao.insert(guestbook);
        return ObjectConvertUtils.convert(guestbook, LeaveMessageEntity.class);
    }

    @Override
    public boolean deleteLeaveMessage(Integer id) {
        return guestbookDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteLeaveMessage(Integer[] ids) {
        return guestbookDao.deleteByIds(List.of(ids)) > 0;
    }

    @Override
    public boolean auditLeaveMessage(Integer id, Integer review) {
        LambdaUpdateWrapper<Guestbook> guestbookLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        guestbookLambdaUpdateWrapper
                .eq(Guestbook::getId, id)
                .set(Guestbook::getReview, review);
        return guestbookDao.update(null, guestbookLambdaUpdateWrapper) > 0;
    }

    @Override
    public boolean updateLeaveMessageHidden(Integer id, Integer hidden) {
        LambdaUpdateWrapper<Guestbook> guestbookLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        guestbookLambdaUpdateWrapper
                .eq(Guestbook::getId, id)
                .set(Guestbook::getHidden, hidden);
        return guestbookDao.update(null, guestbookLambdaUpdateWrapper) > 0;
    }

    @Override
    public PageData<LeaveMessageEntity> getPageLeaveMessages(PageParams pageParams, LeaveMessageQueryEntity leaveMessageQueryEntity) {
        Page<Guestbook> page = new Page<>(pageParams.getNumber(), pageParams.getSize());
        LambdaQueryWrapper<Guestbook> guestbookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        guestbookLambdaQueryWrapper
                .eq(ObjectUtils.isNotEmpty(leaveMessageQueryEntity.getId()), Guestbook::getId, leaveMessageQueryEntity.getId())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getUsername()), Guestbook::getUsername, leaveMessageQueryEntity.getUsername())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getIpSource()), Guestbook::getIpSource, leaveMessageQueryEntity.getIpSource())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getBrowser()), Guestbook::getBrowser, leaveMessageQueryEntity.getBrowser())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getDevice()), Guestbook::getDevice, leaveMessageQueryEntity.getDevice())
                .eq(ObjectUtils.isNotEmpty(leaveMessageQueryEntity.getHidden()), Guestbook::getHidden, leaveMessageQueryEntity.getHidden())
                .eq(ObjectUtils.isNotEmpty(leaveMessageQueryEntity.getReview()), Guestbook::getReview, leaveMessageQueryEntity.getReview())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getType()), Guestbook::getType, leaveMessageQueryEntity.getType())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getNickname()), Guestbook::getNickname, leaveMessageQueryEntity.getNickname())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getEmail()), Guestbook::getEmail, leaveMessageQueryEntity.getEmail())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getQq()), Guestbook::getQq, leaveMessageQueryEntity.getQq())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getContent()), Guestbook::getContent, leaveMessageQueryEntity.getContent())
                .between(ObjectUtils.isNotEmpty(leaveMessageQueryEntity.getBeginCreateTime())&&ObjectUtils.isNotEmpty(leaveMessageQueryEntity.getEndCreateTime()), Guestbook::getCreateTime, leaveMessageQueryEntity.getBeginCreateTime(), leaveMessageQueryEntity.getEndCreateTime())
                .orderByDesc(Guestbook::getCreateTime);
        page = guestbookDao.selectPage(page, guestbookLambdaQueryWrapper);
        List<LeaveMessageEntity> leaveMessageEntities = ObjectConvertUtils.convertList(page.getRecords(), LeaveMessageEntity.class);
        return PageUtils.convertPageData((int) page.getCurrent(), (int) page.getSize(),page.getTotal(),leaveMessageEntities);
    }

    @Override
    public PageData<LeaveMessageEntity> getPageOfNoAuditLeaveMessages(PageParams pageParams, LeaveMessageQueryEntity leaveMessageQueryEntity) {
        Page<Guestbook> page = new Page<>(pageParams.getNumber(), pageParams.getSize());
        LambdaQueryWrapper<Guestbook> guestbookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        guestbookLambdaQueryWrapper
                .eq(ObjectUtils.isNotEmpty(leaveMessageQueryEntity.getId()), Guestbook::getId, leaveMessageQueryEntity.getId())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getUsername()), Guestbook::getUsername, leaveMessageQueryEntity.getUsername())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getIpSource()), Guestbook::getIpSource, leaveMessageQueryEntity.getIpSource())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getBrowser()), Guestbook::getBrowser, leaveMessageQueryEntity.getBrowser())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getDevice()), Guestbook::getDevice, leaveMessageQueryEntity.getDevice())
                .eq(ObjectUtils.isNotEmpty(leaveMessageQueryEntity.getHidden()), Guestbook::getHidden, leaveMessageQueryEntity.getHidden())
                .eq(ObjectUtils.isNotEmpty(leaveMessageQueryEntity.getReview()), Guestbook::getReview,0)
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getType()), Guestbook::getType, leaveMessageQueryEntity.getType())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getNickname()), Guestbook::getNickname, leaveMessageQueryEntity.getNickname())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getEmail()), Guestbook::getEmail, leaveMessageQueryEntity.getEmail())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getQq()), Guestbook::getQq, leaveMessageQueryEntity.getQq())
                .like(StringUtils.isNotBlank(leaveMessageQueryEntity.getContent()), Guestbook::getContent, leaveMessageQueryEntity.getContent())
                .between(ObjectUtils.isNotEmpty(leaveMessageQueryEntity.getBeginCreateTime())&&ObjectUtils.isNotEmpty(leaveMessageQueryEntity.getEndCreateTime()), Guestbook::getCreateTime, leaveMessageQueryEntity.getBeginCreateTime(), leaveMessageQueryEntity.getEndCreateTime())
                .orderByDesc(Guestbook::getCreateTime);
        page = guestbookDao.selectPage(page, guestbookLambdaQueryWrapper);
        List<LeaveMessageEntity> leaveMessageEntities = ObjectConvertUtils.convertList(page.getRecords(), LeaveMessageEntity.class);
        return PageUtils.convertPageData((int) page.getCurrent(), (int) page.getSize(),page.getTotal(),leaveMessageEntities);
    }

    @Override
    public PageData<LeaveMessageSimpleEntity> getPageLeaveMessagesByUser(PageParams pageParams) {
        Page<Guestbook> page = new Page<>(pageParams.getNumber(), pageParams.getSize());
        LambdaQueryWrapper<Guestbook> guestbookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        guestbookLambdaQueryWrapper
                .select(Guestbook::getId,Guestbook::getNickname,Guestbook::getContent,Guestbook::getCreateTime,Guestbook::getIpAddress,Guestbook::getIpSource,Guestbook::getLocation,Guestbook::getImages,Guestbook::getPoint,Guestbook::getEmail,Guestbook::getQq,Guestbook::getBrowser,Guestbook::getDevice,Guestbook::getType)
                .eq(Guestbook::getHidden,0)
                .eq(Guestbook::getReview,1)
                .orderByDesc(Guestbook::getCreateTime);
        page = guestbookDao.selectPage(page, guestbookLambdaQueryWrapper);
        List<LeaveMessageSimpleEntity> leaveMessageEntities = ObjectConvertUtils.convertList(page.getRecords(), LeaveMessageSimpleEntity.class);
        return PageUtils.convertPageData((int) page.getCurrent(), (int) page.getSize(),page.getTotal(),leaveMessageEntities);
    }
}
