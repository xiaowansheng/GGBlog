package com.wbxnl.blog.service;

import com.wbxnl.blog.model.dto.CommentDto;
import com.wbxnl.blog.model.dto.UserDto;
import com.wbxnl.blog.model.entity.Guestbook;
import jakarta.validation.constraints.Pattern;

/**
 * @Author xiaowansheng
 * @Date 2023/8/17 13:56
 */
public interface EmailService {
    /**
     * 给指定邮箱发送验证码
     * @param receiverMailbox
     * @param code
     * @return
     */
    boolean sendVerification(@Pattern(regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$", message = "邮箱格式不正确") String receiverMailbox, String code);

    /**
     * 用新用户注册，提醒站长
     *      是否通知还需要根据配置
     * @param email
     * @param nickname
     * @param ipAddress
     * @param ipSource
     */
    void registerNotice(String email, String nickname, String ipAddress, String ipSource);

    /**
     * 有新评论，通知相关的用户
     *      是否通知还需要根据配置
     * @param topicType 评论的话题类型
     * @param url 话题所在的链接地址
     * @param targetCommentId 被评论的那条评论id，没有则是根评论
     * @param nicknameOfSource 评论人的昵称
     * @param commentOfSource 评论的具体内容
     * @param ipAddressOfSource 评论的ip
     * @param ipSourceOfSource 评论的来源
     * @param deviceOfSource 评论的设备
     * @param browserOfSource 评论的浏览器
     */
    void commentNotice(String topicType,String url,Integer targetCommentId,String nicknameOfSource,String commentOfSource,String ipAddressOfSource,String ipSourceOfSource,String deviceOfSource,String browserOfSource);

    /**
     * 用户上线，通知站长
     *      是否通知还需要根据配置
     * @param userDto
     * @param ipAddress
     * @param ipSource
     * @param deviceType
     * @param browserName
     */
    void loginNotice(UserDto userDto, String ipAddress, String ipSource, String deviceType, String browserName);

    /**
     * 有新留言通知站长
     *      是否通知还需要根据配置
     * @param guestbook
     */
    void leaveWordNotice(Guestbook guestbook);
}
