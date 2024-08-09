package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.RoleDataReq;
import com.wbxnl.blog.api.admin.model.res.SystemMenuSimpleRes;
import com.wbxnl.blog.api.admin.model.res.SystemResourceSimpleRes;
import com.wbxnl.blog.api.admin.service.IRoleService;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.KeyData;
import com.wbxnl.blog.domain.authority.model.eneity.RoleEntity;
import com.wbxnl.blog.domain.authority.model.eneity.RoleUpdateEntity;
import com.wbxnl.blog.domain.authority.model.vo.RoleMenuVo;
import com.wbxnl.blog.domain.authority.model.vo.RoleVo;
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
        RoleVo roleVo = ObjectConvertUtils.convert(roleDataReq, RoleVo.class);
        RoleEntity roleEntity = roleService.addRole(roleVo);
        return KeyData.builder()
                .id(roleEntity.getId())
                .key(roleEntity.getRoleKey())
                .build();
    }

    @Override
    public void deleteRole(Integer id) {
        roleService.deleteRole(id);
    }

    @Override
    public void deleteRole(Integer[] ids) {
        roleService.deleteRole(ids);
    }

    @Override
    public void updateRole(RoleDataReq roleDataReq) {
        RoleUpdateEntity roleUpdateEntity = ObjectConvertUtils.convert(roleDataReq, RoleUpdateEntity.class);
        roleService.updateRole(roleUpdateEntity);
    }

    @Override
    public void addUserRole(String username, String roleKey) {
        // TODO
    }

    @Override
    public void deleteUserRole(String username, String roleKey) {
        // TODO
    }

    @Override
    public void addRoleMenu(String roleKey, List<String> menuKeys) {
        RoleMenuVo roleMenuVo = new RoleMenuVo(roleKey, menuKeys);
        roleService.updateRoleMenu(roleMenuVo);
    }

    @Override
    public List<SystemMenuSimpleRes> getSystemMenus(String roleKey) {
        List<String> menuNameList = roleService.getMenuNameList(roleKey);
        return ObjectConvertUtils.convertList(menuNameList, SystemMenuSimpleRes.class);
    }

    @Override
    public void addRoleResource(String roleKey, List<String> resourceKeys) {

    }

    @Override
    public List<SystemResourceSimpleRes> getSystemResources(String roleKey) {
        return List.of();
    }
}
