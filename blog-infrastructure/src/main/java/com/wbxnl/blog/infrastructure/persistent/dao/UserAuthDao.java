package com.wbxnl.blog.infrastructure.persistent.dao;

import com.wbxnl.blog.infrastructure.persistent.dao.base.BaseDao;
import com.wbxnl.blog.infrastructure.persistent.po.UserAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户账号 Mapper 接口
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Mapper
public interface UserAuthDao extends BaseDao<UserAuth> {
//    /**
//     * 获取用户详细信息
//     * @return
//     */
//    List<UserAuthDto> getUserList(@Param("current")Long current, @Param("size")Long size, @Param("userAuthParams") UserAuthParams userAuthParams);
//
//    /**
//     * 根据条件查询数量
//     * @param userAuthParams
//     * @return
//     */
//    Long getCount(@Param("userAuthParams")  UserAuthParams userAuthParams);
}
