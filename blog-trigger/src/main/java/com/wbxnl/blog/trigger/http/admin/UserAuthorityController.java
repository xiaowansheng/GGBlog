package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.res.SystemMenuInfoRes;
import com.wbxnl.blog.api.admin.service.IUserAuthorityService;
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
    public void addUserRole(String username, String roleKey) {

    }

    @Override
    public void deleteUserRole(String username, String roleKey) {

    }

    @Override
    public List<SystemMenuInfoRes> getSystemMenus() {
        return List.of();
    }
}
