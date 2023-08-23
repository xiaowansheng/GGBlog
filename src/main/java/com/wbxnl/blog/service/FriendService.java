package com.wbxnl.blog.service;

import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.model.dto.FriendDto;
import com.wbxnl.blog.model.entity.Friend;
import com.wbxnl.blog.model.vo.FriendVo;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.service.base.BaseService;

/**
 * <p>
 * 友情链接 服务类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
public interface FriendService extends BaseService<Friend, FriendDto, FriendVo> {

}
