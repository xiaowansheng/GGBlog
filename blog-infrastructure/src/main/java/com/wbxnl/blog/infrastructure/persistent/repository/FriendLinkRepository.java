package com.wbxnl.blog.infrastructure.persistent.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkEntity;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkQueryEntity;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkSimpleEntity;
import com.wbxnl.blog.domain.friendLink.model.entity.FriendLinkUpdateEntity;
import com.wbxnl.blog.domain.friendLink.model.vo.FriendLinkVo;
import com.wbxnl.blog.domain.friendLink.repository.IFriendLinkRepository;
import com.wbxnl.blog.infrastructure.persistent.dao.FriendDao;
import com.wbxnl.blog.infrastructure.persistent.po.Friend;
import com.wbxnl.blog.infrastructure.persistent.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/31 16:53
 */
@Service
@RequiredArgsConstructor
public class FriendLinkRepository implements IFriendLinkRepository {

    private final FriendDao friendDao;

    @Override
    public FriendLinkEntity addFriendLink(FriendLinkVo friendLinkVo) {
        Friend friend = ObjectConvertUtils.convert(friendLinkVo, Friend.class);
        int insert = friendDao.insert(friend);
        if (insert <= 0) {
            return null;
        }
        return ObjectConvertUtils.convert(friend, FriendLinkEntity.class);
    }

    @Override
    public boolean auditFriendLink(Integer id, Integer review) {
        LambdaUpdateWrapper<Friend> friendLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        friendLambdaUpdateWrapper
                .eq(Friend::getId, id)
                .set(Friend::getReview, review);
        return friendDao.update(null, friendLambdaUpdateWrapper) > 0;
    }

    @Override
    public FriendLinkEntity getFriendLink(Integer id) {
        Friend friend = friendDao.selectById(id);
        return ObjectConvertUtils.convert(friend, FriendLinkEntity.class);
    }

    @Override
    public boolean deleteFriendLink(Integer id) {
        return friendDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteFriendLink(Integer[] ids) {
        return friendDao.deleteByIds(List.of(ids)) > 0;
    }

    @Override
    public boolean updateFriendLink(FriendLinkUpdateEntity friendLinkUpdateEntity) {
        Friend friend = ObjectConvertUtils.convert(friendLinkUpdateEntity, Friend.class);
        return friendDao.updateById(friend) > 0;
    }

    @Override
    public boolean updateFriendLinkHidden(Integer id, Integer hidden) {
        LambdaUpdateWrapper<Friend> friendLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        friendLambdaUpdateWrapper
                .eq(Friend::getId, id)
                .set(Friend::getHidden, hidden);
        return friendDao.update(null, friendLambdaUpdateWrapper) > 0;
    }

    @Override
    public PageData<FriendLinkEntity> getPageFriendLinks(PageParams pageParams, FriendLinkQueryEntity friendLinkQueryEntity) {
        Page<Friend> page = new Page<>(pageParams.getNumber(), pageParams.getSize());
        LambdaQueryWrapper<Friend> friendLambdaQueryWrapper = new LambdaQueryWrapper<>();
        friendLambdaQueryWrapper
                .eq(ObjectUtils.isNotEmpty(friendLinkQueryEntity.getId()), Friend::getId, friendLinkQueryEntity.getId())
                .eq(ObjectUtils.isNotEmpty(friendLinkQueryEntity.getReview()), Friend::getReview, friendLinkQueryEntity.getReview())
                .eq(ObjectUtils.isNotEmpty(friendLinkQueryEntity.getHidden()), Friend::getHidden, friendLinkQueryEntity.getHidden())
                .like(StringUtils.isNotBlank(friendLinkQueryEntity.getUsername()), Friend::getUsername, friendLinkQueryEntity.getUsername())
                .like(StringUtils.isNotBlank(friendLinkQueryEntity.getName()), Friend::getName, friendLinkQueryEntity.getName())
                .like(StringUtils.isNotBlank(friendLinkQueryEntity.getUrl()), Friend::getUrl, friendLinkQueryEntity.getUrl())
                .like(StringUtils.isNotBlank(friendLinkQueryEntity.getUsername()), Friend::getUsername, friendLinkQueryEntity.getUsername())
                .like(StringUtils.isNotBlank(friendLinkQueryEntity.getIntroduction()), Friend::getIntroduction, friendLinkQueryEntity.getIntroduction())
                .like(StringUtils.isNotBlank(friendLinkQueryEntity.getAuthor()), Friend::getAuthor, friendLinkQueryEntity.getAuthor())
                .between(ObjectUtils.isNotEmpty(friendLinkQueryEntity.getBeginCreateTime())&&ObjectUtils.isNotEmpty(friendLinkQueryEntity.getEndCreateTime()), Friend::getCreateTime, friendLinkQueryEntity.getBeginCreateTime(), friendLinkQueryEntity.getEndCreateTime())
                .orderByDesc(Friend::getCreateTime);
        page= friendDao.selectPage(page, friendLambdaQueryWrapper);
        List<FriendLinkEntity> friendLinkEntities = ObjectConvertUtils.convertList(page.getRecords(), FriendLinkEntity.class);
        return PageUtils.convertPageData(pageParams.getNumber(), pageParams.getSize(), page.getTotal(), friendLinkEntities);
    }

    @Override
    public PageData<FriendLinkEntity> getPageOfNoAuditFriendLinks(PageParams pageParams, FriendLinkQueryEntity friendLinkQueryEntity) {
        Page<Friend> page = new Page<>(pageParams.getNumber(), pageParams.getSize());
        LambdaQueryWrapper<Friend> friendLambdaQueryWrapper = new LambdaQueryWrapper<>();
        friendLambdaQueryWrapper
                .eq(ObjectUtils.isNotEmpty(friendLinkQueryEntity.getId()), Friend::getId, friendLinkQueryEntity.getId())
                .eq(Friend::getReview, 0)
                .eq(ObjectUtils.isNotEmpty(friendLinkQueryEntity.getHidden()), Friend::getHidden, friendLinkQueryEntity.getHidden())
                .like(StringUtils.isNotBlank(friendLinkQueryEntity.getUsername()), Friend::getUsername, friendLinkQueryEntity.getUsername())
                .like(StringUtils.isNotBlank(friendLinkQueryEntity.getName()), Friend::getName, friendLinkQueryEntity.getName())
                .like(StringUtils.isNotBlank(friendLinkQueryEntity.getUrl()), Friend::getUrl, friendLinkQueryEntity.getUrl())
                .like(StringUtils.isNotBlank(friendLinkQueryEntity.getUsername()), Friend::getUsername, friendLinkQueryEntity.getUsername())
                .like(StringUtils.isNotBlank(friendLinkQueryEntity.getIntroduction()), Friend::getIntroduction, friendLinkQueryEntity.getIntroduction())
                .like(StringUtils.isNotBlank(friendLinkQueryEntity.getAuthor()), Friend::getAuthor, friendLinkQueryEntity.getAuthor())
                .between(ObjectUtils.isNotEmpty(friendLinkQueryEntity.getBeginCreateTime())&&ObjectUtils.isNotEmpty(friendLinkQueryEntity.getEndCreateTime()), Friend::getCreateTime, friendLinkQueryEntity.getBeginCreateTime(), friendLinkQueryEntity.getEndCreateTime())
                .orderByDesc(Friend::getCreateTime);
        page= friendDao.selectPage(page, friendLambdaQueryWrapper);
        List<FriendLinkEntity> friendLinkEntities = ObjectConvertUtils.convertList(page.getRecords(), FriendLinkEntity.class);
        return PageUtils.convertPageData(pageParams.getNumber(), pageParams.getSize(), page.getTotal(), friendLinkEntities);    }

    @Override
    public PageData<FriendLinkSimpleEntity> getPageFriendLinksByUser(PageParams pageParams) {
        Page<Friend> page = new Page<>(pageParams.getNumber(), pageParams.getSize());
        LambdaQueryWrapper<Friend> friendLambdaQueryWrapper = new LambdaQueryWrapper<>();
        friendLambdaQueryWrapper
                .eq(Friend::getReview, 1)
                .eq(Friend::getHidden, 0)
                .orderByDesc(Friend::getCreateTime);
        page= friendDao.selectPage(page, friendLambdaQueryWrapper);
        List<FriendLinkSimpleEntity> friendLinkEntities = ObjectConvertUtils.convertList(page.getRecords(), FriendLinkSimpleEntity.class);
        return PageUtils.convertPageData(pageParams.getNumber(), pageParams.getSize(), page.getTotal(), friendLinkEntities);
    }
}
