package com.wbxnl.blog.trigger.http.admin;

import com.wbxnl.blog.api.admin.model.req.SystemMenuDataReq;
import com.wbxnl.blog.api.admin.model.req.SystemMenuQueryReq;
import com.wbxnl.blog.api.admin.model.req.SystemResourceDataReq;
import com.wbxnl.blog.api.admin.model.req.SystemResourceQueryReq;
import com.wbxnl.blog.api.admin.model.res.SystemMenuDetailRes;
import com.wbxnl.blog.api.admin.model.res.SystemResourceDetailRes;
import com.wbxnl.blog.api.admin.service.IAuthorityService;
import com.wbxnl.blog.api.admin.service.IRoleService;
import com.wbxnl.blog.api.admin.service.IUserAuthorityService;
import com.wbxnl.blog.common.utils.ObjectConvertUtils;
import com.wbxnl.blog.common.vo.KeyData;
import com.wbxnl.blog.common.vo.PageData;
import com.wbxnl.blog.common.vo.PageParams;
import com.wbxnl.blog.domain.authority.model.eneity.SystemMenuEntity;
import com.wbxnl.blog.domain.authority.model.eneity.SystemMenuUpdateEntity;
import com.wbxnl.blog.domain.authority.model.vo.SystemMenuVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/8/6 10:59
 */
@Slf4j
@RestController
@RequestMapping("/admin/authority")
@RequiredArgsConstructor
public class AuthorityController implements IAuthorityService {

    private final com.wbxnl.blog.domain.authority.service.IAuthorityService authorityService;

    private final IRoleService roleService;

    private final IUserAuthorityService userAuthorityService;

    @Override
    public KeyData addSystemMenu(SystemMenuDataReq systemMenuDataReq) {
        SystemMenuVo systemMenuVo = ObjectConvertUtils.convert(systemMenuDataReq, SystemMenuVo.class);
        SystemMenuEntity systemMenuEntity = authorityService.addSystemMenu(systemMenuVo);
        return KeyData.builder()
                .id(systemMenuEntity.getId())
                .key(systemMenuEntity.getMenuKey())
                .build();
    }

    @Override
    public void updateSystemMenu(SystemMenuDataReq systemMenuDataReq) {
        SystemMenuUpdateEntity systemMenuUpdateEntity = ObjectConvertUtils.convert(systemMenuDataReq, SystemMenuUpdateEntity.class);
        authorityService.updateSystemMenu(systemMenuUpdateEntity);
    }

    @Override
    public void updateSystemMenuStatus(Integer id, Integer status) {

    }

    @Override
    public void deleteSystemMenu(Integer id) {

    }

    @Override
    public void deleteSystemMenu(Integer[] ids) {

    }

    @Override
    public PageData<SystemMenuDetailRes> getPageOfSystemMenu(PageParams pageParams, SystemMenuQueryReq systemMenuQueryReq) {
        return null;
    }

    @Override
    public KeyData addSystemResource(SystemResourceDataReq systemMenuDataReq) {
        return null;
    }

    @Override
    public void updateSystemResource(SystemResourceDataReq systemResourceDataReq) {

    }

    @Override
    public void updateSystemResourceStatus(Integer id, Integer status) {

    }

    @Override
    public void deleteSystemResource(Integer id) {

    }

    @Override
    public void deleteSystemResource(Integer[] ids) {

    }

    @Override
    public PageData<SystemResourceDetailRes> getPageOfSystemResource(PageParams pageParams, SystemResourceQueryReq systemResourceQueryReq) {
        return null;
    }
}
