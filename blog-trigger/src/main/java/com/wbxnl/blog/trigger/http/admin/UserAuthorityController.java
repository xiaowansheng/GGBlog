package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.res.SystemMenuInfoRes;
import com.wbxnl.blog.api.admin.service.IUserAuthorityService;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.domain.authority.model.eneity.UserMenuEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/6 11:21
 */
@Slf4j
@RestController
@RequestMapping("/admin/user/authority")
@RequiredArgsConstructor
public class UserAuthorityController implements IUserAuthorityService {

    private final com.wbxnl.blog.domain.authority.service.IUserAuthorityService userAuthorityService;

    @Override
    public void updateUserRole(String username, String roleKey) {
        userAuthorityService.updateUserRole(username, roleKey);
    }

    @Override
    public List<SystemMenuInfoRes> getSystemMenus() {
        // TODO 获取当前用户信息
        String username="";
        List<UserMenuEntity> userRoleMenu = userAuthorityService.getUserMenu(username);
        return ObjectConvertUtils.convertList(userRoleMenu, SystemMenuInfoRes.class);
    }
}
