package com.wbxnl.blog.service.impl;

import com.wbxnl.blog.model.dto.UserInfoDto;
import com.wbxnl.blog.model.entity.UserInfo;
import com.wbxnl.blog.dao.UserInfoDao;
import com.wbxnl.blog.model.vo.UserInfoVo;
import com.wbxnl.blog.service.UserInfoService;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import com.wbxnl.blog.utils.SecurityUtils;
import com.wbxnl.blog.utils.SensitiveWordUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
public class UserInfoServiceImpl extends AbstractServiceImpl<UserInfoDao, UserInfo, UserInfoDto, UserInfoVo> implements UserInfoService {

    @Override
    public void update(UserInfoVo vo) {
        // 填入当前用户的信息
        vo.setId(SecurityUtils.getUserInfoId());
        // 敏感词检测，替换
        vo.setNickname(SensitiveWordUtils.replace(vo.getNickname()));
        vo.setSignature(SensitiveWordUtils.replace(vo.getSignature()));
        vo.setIntroduction(SensitiveWordUtils.replace(vo.getIntroduction()));
        super.update(vo);
    }

    @Override
    public UserInfo saveVo(UserInfoVo vo) {
        // 敏感词检测，替换
        vo.setNickname(SensitiveWordUtils.replace(vo.getNickname()));
        vo.setSignature(SensitiveWordUtils.replace(vo.getSignature()));
        vo.setIntroduction(SensitiveWordUtils.replace(vo.getIntroduction()));
        return super.saveVo(vo);
    }
}
