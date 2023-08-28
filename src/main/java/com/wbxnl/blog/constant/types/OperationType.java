package com.wbxnl.blog.constant.types;

/**
 * @author wansheng
 * @createDate 2022/8/27 1:31
 */
public interface OperationType {
    /**
     * 添加操作
     */
    String ADD="添加";
    /**
     * 批量添加操作
     */
    String ADD_PATCH="批量添加";
    /**
     * 修改操作
     */
    String UPDATE="修改";
    /**
     * 批量修改操作
     */
    String UPDATE_PATCH="批量修改";
    /**
     * 删除操作
     */
    String DELETE="删除";
    /**
     * 批量删除操作
     */
    String DELETE_PATCH="批量删除";
    /**
     * 导出操作
     */
    String EXPORT="导出";

//    /**
//     * 登录操作
//     */
//    String LOGIN="登录";
//    /**
//     * 注销操作
//     */
//    String LOGOUT="注销";
    /**
     * 测试操作
     */
    String TEST="测试";
}
