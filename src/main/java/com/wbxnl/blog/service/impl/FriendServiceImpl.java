package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.model.dto.FriendDto;
import com.wbxnl.blog.model.entity.Friend;
import com.wbxnl.blog.dao.FriendDao;
import com.wbxnl.blog.model.vo.FriendVo;
import com.wbxnl.blog.model.vo.params.FriendParams;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.QueryParams;
import com.wbxnl.blog.service.FriendService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import com.wbxnl.blog.utils.ConvertUtils;
import com.wbxnl.blog.utils.SecurityUtils;
import com.wbxnl.blog.utils.SensitiveWordUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 友情链接 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class FriendServiceImpl extends AbstractServiceImpl<FriendDao, Friend, FriendDto, FriendVo> implements FriendService {

    @Override
    public void setQueryParamsByUser(QueryParams queryParams) {
        FriendParams friendParams = (FriendParams) queryParams;
        // 审核通过
        friendParams.setReview(1);
        // 公开状态
        friendParams.setHidden(0);
    }

    @Override
    public LambdaQueryWrapper<Friend> getQueryWrapper(QueryParams queryParams) {
        FriendParams friendParams = (FriendParams) queryParams;
        LambdaQueryWrapper<Friend> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotNull(friendParams.getId())) {
            queryWrapper.eq(Friend::getId, friendParams.getId());
        }
        if (ObjectUtils.isNotNull(friendParams.getUserAuthId())) {
            queryWrapper.eq(Friend::getUserAuthId, friendParams.getUserAuthId());
        }
        if (StringUtils.isNotBlank(friendParams.getName())) {
            queryWrapper.like(Friend::getName, friendParams.getName());
        }
        if (StringUtils.isNotBlank(friendParams.getAuthor())) {
            queryWrapper.like(Friend::getAuthor, friendParams.getAuthor());
        }
        if (StringUtils.isNotBlank(friendParams.getIntroduction())) {
            queryWrapper.like(Friend::getIntroduction, ((FriendParams) queryParams).getIntroduction());
        }
        if (ObjectUtils.isNotNull(friendParams.getReview())) {
            queryWrapper.eq(Friend::getReview, friendParams.getReview());
        }
        queryWrapper.orderByAsc(Friend::getCreateTime);
        return queryWrapper;
    }

    @Override
    public Friend saveVo(FriendVo vo) {
        Friend friend = ConvertUtils.sourceToTarget(vo, Friend.class);
        // 登录用户才能提交友链申请
        friend.setUserAuthId(SecurityUtils.getUserAuthId());
        // 需要审核
        friend.setReview(0);
        // 友链需要审核相关网站内容
//        vo.setName(SensitiveWordUtils.replace(vo.getName()));
//        vo.setAuthor(SensitiveWordUtils.replace(vo.getAuthor()));
//        vo.setIntroduction(SensitiveWordUtils.replace(vo.getIntroduction()));
        save(friend);
        return friend;
    }
}
