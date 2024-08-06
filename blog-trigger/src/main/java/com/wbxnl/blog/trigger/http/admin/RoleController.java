package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.RoleDataReq;
import com.wbxnl.blog.api.admin.model.res.SystemMenuSimpleRes;
import com.wbxnl.blog.api.admin.model.res.SystemResourceSimpleRes;
import com.wbxnl.blog.api.admin.service.IRoleService;
import com.wbxnl.blog.common.vo.KeyData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/6 11:17
 */
@Slf4j
@RestController
@RequestMapping("/admin/role")
@RequiredArgsConstructor
public class RoleController implements IRoleService {

    private final com.wbxnl.blog.domain.authority.service.IRoleService roleService;

    @Override
    public KeyData addRole(RoleDataReq roleDataReq) {
        return null;
    }

    @Override
    public void deleteRole(Integer id) {

    }

    @Override
    public void deleteRole(Integer[] ids) {

    }

    @Override
    public void updateRole(RoleDataReq roleDataReq) {

    }

    @Override
    public void addUserRole(String username, String roleKey) {

    }

    @Override
    public void deleteUserRole(String username, String roleKey) {

    }

    @Override
    public void addRoleMenu(String roleKey, List<String> menuKeys) {

    }

    @Override
    public List<SystemMenuSimpleRes> getSystemMenus(String roleKey) {
        return List.of();
    }

    @Override
    public void addRoleResource(String roleKey, List<String> resourceKeys) {

    }

    @Override
    public List<SystemResourceSimpleRes> getSystemResources(String roleKey) {
        return List.of();
    }
}
