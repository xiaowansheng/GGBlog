package com.wbxnl.blog.enums;

import lombok.AllArgsConstructor;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @author wansheng
 * @createDate 2022/8/27 18:28
 */
@AllArgsConstructor
@Getter
public enum OperationStateCode {
    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 失败
     */
    FAILURE(40000, "操作失败"),

//    用户
    /**
     * 注册信息错误
     */
    SIGNUP_FAILURE(40001, "注册失败！"),
    /**
     * 验证码错误
     */
    VERIFICATION_ERROR(40002, "验证码错误！"),
    /**
     * 账号已被注册
     */
    ACCOUNT_EXIST(40003, "账号已被注册！"),
    /**
     * 未设置角色信息
     */
    GRANT_ROLE_FAILURE(40004, "角色授权失败！"),
    /**
     * 验证码发送失败
     */
    SEND_CODE_FAILURE(40005, "验证码发送失败！"),
    /**
     * 密码重置错误
     */
    RESET_PASSWORD_FAILURE(40006, "密码重置失败！"),
    /**
     * 重置信息有误
     */
    RESET_INFO_ERROR(40006, "重置信息有误！"),
    /**
     * 账号不存在
     */
    ACCOUNT_NOT_EXIST(40007, "账号不存在！"),
    /**
     * token无效
     */
    TOKEN_INVALID(40008, "token无效"),
    /**
     * 刷新token无效
     */
    REFRESH_TOKEN_INVALID(40009, "refresh_token无效"),
    /**
     * token已过期
     */
    TOKEN_EXPIRE(40010, "Token已过期！"),
    /**
     * 刷新的token过期，需要重新登录
     */
    REFRESH_TOKEN_EXPIRE(40011, "登录凭证已过期，请重新登录！"),
    /**
     * 用户未授权
     */
    NO_RIGHT(41000, "未认证或授权！！"),
    /**
     * 用户未登录
     */
    NO_LOGIN(41001, "您还未登录，请登录后重试！"),
    /**
     * 用户名为空
     */
    NULL_USERNAME(41002, "用户名不能为空！"),
    /**
     * 用户不存在
     */
    NULL_USER(41003, "当前用户不存在！"),
    /**
     * 用户被禁用
     */
    USER_DISABLE(41003, "您已被禁用！"),
    /**
     * 登录失败
     */
    LOGIN_FAILURE(41003, "用户名或密码错误！！"),
    /**
     * 没有角色信息
     */
    NULL_ROLE(41004, "当前用户没有角色信息！"),
    /**
     * token非法
     */
    TOKEN_ILLEGAL(41005, "token非法！"),
    /**
     * 密码错误
     */
    PASSWORD_ERROR(41006, "密码错误！！"),
    /**
     * 参数错误
     */
    PARAMS_ERROR(41007, "参数错误！！"),

    /**
     * 邮箱不合法
     */
    EMAIL_INVALIDATE(41008, "邮箱不合法！！"),
    //    用户授权
    AUTHENTICATION_FAILURE(42001, "您没有操作权限哦！"),

    //    操作
    CONVERT_DATA_FAILURE(43001, "数据转换异常！"),

    // 其他错误
    /**
     * 参数校验失败
     */
    ARGUMENT_NOT_VALID(40002, "参数校验失败！"),
    /**
     * 方法不支持
     */
    METHOD_NOT_SUPPORTED(43003, "请求方法不支持！"),
    /**
     * 枚举类型
     */
    ENUM_TYPE_NO_EXIST(43004, "枚举的类型不是预定义类型！"),
    /**
     * 请求的参数丢失，导致参数不匹配
     */
    MISSING_PARAMETER(43005, "请求的参数不匹配！"),
    /**
     * JSON字符串格式错误
     */
    JSON_FORMAT_ERROR(43006, "JSON字符串格式错误,不能转为JSON"),
    /**
     * 文件不能为空
     */
    FILE_IS_EMPTY(43007, "文件不能为空！"),
    /**
     * 文件保存失败
     */
    FILE_SAVE_FAILURE(43008, "文件保存失败！");
    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String message;

}
