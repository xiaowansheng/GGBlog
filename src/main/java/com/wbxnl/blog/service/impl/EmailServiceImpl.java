package com.wbxnl.blog.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wbxnl.blog.enums.LoginTypeEmus;
import com.wbxnl.blog.enums.TopicTypeEums;
import com.wbxnl.blog.enums.UserTypeEums;
import com.wbxnl.blog.model.config.Notice;
import com.wbxnl.blog.model.dto.CommentDto;
import com.wbxnl.blog.model.dto.MailDto;
import com.wbxnl.blog.model.dto.SystemConfigDto;
import com.wbxnl.blog.model.dto.UserDto;
import com.wbxnl.blog.model.entity.Comment;
import com.wbxnl.blog.model.entity.Guestbook;
import com.wbxnl.blog.model.entity.UserAuth;
import com.wbxnl.blog.model.entity.UserInfo;
import com.wbxnl.blog.service.*;
import com.wbxnl.blog.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author xiaowansheng
 * @Date 2023/8/17 13:57
 */
@Service
public class EmailServiceImpl implements EmailService {
    /**
     * 验证码邮件模板名称
     */
    private static final String VERIFICATION_TEMPLATE_NAME = "verificationCode";
    /**
     * 注册通知邮件模板名
     */
    private static final String REGISTER_NOTICE_TEMPLATE_NAME = "registerNotice";
    /**
     * 登录通知邮件模板名
     */
    private static final String LOGIN_NOTICE_TEMPLATE_NAME = "loginNotice";
    /**
     * 评论通知邮件模板名
     */
    private static final String COMMENT_NOTICE_TEMPLATE_NAME = "commentNotice";
    ;
    /**
     * 留言通知邮件模板名
     */
    private static final String LEAVE_WORD_NOTICE_TEMPLATE_NAME = "leaveWordNotice";
    /**
     * "与我相关"回复通知邮件模板名
     */
    private static final String REPLIED_NOTICE_TEMPLATE_NAME = "repliedNotice";
    @Autowired
    private EmailUtils emailUtils;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private SystemConfigService systemConfigService;
    //    @Autowired
    private CommentService commentService;
    //    @Autowired
    private UserAuthService userAuthService;
    //    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    public void setCommentService(@Lazy CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setUserAuthService(@Lazy UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @Autowired
    public void setUserInfoService(@Lazy UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public boolean sendVerification(String receiverMailbox, String code) {
        Context context = getDefaultContext();
        context.setVariable("title", "嗨,您的验证码已送达~");
        context.setVariable("code", code);
        // 设置使用期限展示
        context.setVariable("expire", 15);
        String htmlStr = generateHtmlStr(VERIFICATION_TEMPLATE_NAME, context);
        return emailUtils.sentHtmlMail(new MailDto(receiverMailbox, "系统验证码", htmlStr));
    }

    @Override
    @Async
    public void registerNotice(String email, String nickname, String ipAddress, String ipSource) {
        if (!isNotice(Notice.REGISTER)) {
            return;
        }
        Context context = getDefaultContext();
        context.setVariable("title", "嘿，你的网站又有了新的用户哦~");
        context.setVariable("email", email);
        context.setVariable("nickname", nickname);
        context.setVariable("ipAddress", ipAddress);
        context.setVariable("ipSource", ipSource);
        String htmlStr = generateHtmlStr(REGISTER_NOTICE_TEMPLATE_NAME, context);
        emailUtils.sentHtmlMail(new MailDto(emailUtils.mailSender, "网站新用户注册通知~", htmlStr));
    }

    @Override
    @Async
    public void commentNotice(String topicType, String url, Integer targetCommentId, String nicknameOfSource, String commentOfSource, String ipAddressOfSource, String ipSourceOfSource, String deviceOfSource, String browserOfSource) {
        /**
         * 需要的信息：
         *          话题类型（为了获取话题标签名）
         *          话题链接 URL
         *      被回复的
         *          昵称
         *          邮箱
         *          评论
         *      回复人的
         *          昵称
         *          回复内容
         */

        /**
         * 0：不通知，1：通知相关的用户，2：通知站长
         */
        int flag = 0;
        if (!isNotice(Notice.RELATE)) {
            // 配置上不通知就退出
            if (!isNotice(Notice.COMMENT)) {
                return;
            } else {
                flag = 2;
            }
        } else {
            flag = 1;
        }
        String topicName = null;
        // 根据话题名称获取话题标签
        TopicTypeEums typeEums = TopicTypeEums.getTopicTypeEums(topicType);
        if(ObjectUtils.isNotNull(typeEums)){
            topicName=typeEums.getLabel();
        }else {
            topicName="【未知】";
        }
        // 获取被回复人的邮箱
        String targetEmail = null;
        if (ObjectUtils.isNull(targetCommentId) || targetCommentId == 0) {
            // 是根评论，根据配置是否通知站长
            if (flag <= 0) {
                return;
            }
            Context context = getDefaultContext();
            targetEmail = emailUtils.mailSender;
            context.setVariable("title", "新的评论信息已送达~");
            context.setVariable("topicName", topicName);
            context.setVariable("url", url);
            context.setVariable("userName", nicknameOfSource);
            context.setVariable("ipAddress", ipAddressOfSource);
            context.setVariable("ipSource", ipSourceOfSource);
            context.setVariable("device", deviceOfSource);
            context.setVariable("browser", browserOfSource);
            context.setVariable("comment", commentOfSource);
            // 使用的是评论通知模板
            MailDto mailDto = new MailDto().setReceiver(targetEmail)
                    .setSubject("作品有了新的评论哟~")
                    .setContent(generateHtmlStr(COMMENT_NOTICE_TEMPLATE_NAME, context));
            emailUtils.sentHtmlMail(mailDto);
        } else {
            // 回复的是用户，则先查询被回复人的信息
            // 根据配置是否需要通知
            if (flag != 1) {
                return;
            }
            Context context = getDefaultContext();
            String targetNickname = null;
            String targetComment = null;
            Comment comment = commentService.lambdaQuery()
                    .select(Comment::getUserAuthId, Comment::getType, Comment::getEmail, Comment::getNickname)
                    .eq(Comment::getId, targetCommentId).one();
            targetComment = comment.getContent();
            // 设置相关信息
            if (UserTypeEums.User.getName().equals(comment.getType())) {
                // TODO 可以优化为一次性查询
                UserAuth userAuth = userAuthService.lambdaQuery().select(UserAuth::getUserInfoId, UserAuth::getUsername, UserAuth::getLoginType).eq(UserAuth::getId, comment.getUserAuthId()).one();
                UserInfo userInfo = userInfoService.lambdaQuery().select(UserInfo::getNickname, UserInfo::getEmail).eq(UserInfo::getId, userAuth.getUserInfoId()).one();
                targetNickname = userInfo.getNickname();
                targetEmail = userInfo.getEmail();
                if (LoginTypeEmus.Email.getName().equals(userAuth.getLoginType())) {
                    targetEmail = userAuth.getUsername();
                }
                if (StringUtils.isBlank(targetEmail)) {
                    // 邮箱都没有，不通知了
                    return;
                }
            } else if (UserTypeEums.Visitor.getName().equals(comment.getType())) {
                // 是游客
                targetEmail = comment.getEmail();
                targetNickname = comment.getNickname();
            } else {
                // 其他类型则退出，因为没有配置
                return;
            }
            // 使用的是被回复的模板
            context.setVariable("topicName", topicName);
            context.setVariable("url", url);
            context.setVariable("targetName", targetNickname);
            context.setVariable("targetComment", targetComment);
            context.setVariable("sourceName", nicknameOfSource);
            context.setVariable("sourceComment", commentOfSource);
            MailDto mailDto = new MailDto().setReceiver(targetEmail)
                    .setSubject("您的评论被小伙伴回复了哟~")
                    .setContent(generateHtmlStr(REPLIED_NOTICE_TEMPLATE_NAME, context));
            emailUtils.sentHtmlMail(mailDto);
        }

    }

    @Override
    @Async
    public void loginNotice(UserDto userDto, String ipAddress, String ipSource, String deviceType, String browserName) {
        // 根据配置是否通知
        if(!isNotice(Notice.LOGIN)){
            return;
        }
        Context context = getDefaultContext();
        String username = userDto.getUsername();
        context.setVariable("title", "用户登录通知已送达~");
        context.setVariable("username", username);
        context.setVariable("ipAddress", ipAddress);
        context.setVariable("ipSource", ipSource);
        context.setVariable("device", deviceType);
        context.setVariable("browser", browserName);
        MailDto mailDto = new MailDto(emailUtils.mailSender, "有用户登录网站了哦", generateHtmlStr(LOGIN_NOTICE_TEMPLATE_NAME, context));
        emailUtils.sentHtmlMail(mailDto);
    }

    @Override
    @Async
    public void leaveWordNotice(Guestbook guestbook) {
        // 根据配置，是否需要通知站长
        if(!isNotice(Notice.LEAVE_WORD)){
            return;
        }
        String userType = guestbook.getType();
        String userTypeName = null;
        String nickname = null;
        String email = null;
        for (UserTypeEums item : UserTypeEums.values()) {
            if (item.getName().equals(userType)) {
                userTypeName = item.getLabel();
            }
        }
        if (StringUtils.isBlank(userTypeName)) {
            userTypeName = "【未知类型】";
        }
        if (UserTypeEums.User.getName().equals(userType)) {
            // 是用户留言
            // TODO 可以优化
            UserAuth userAuth = userAuthService.lambdaQuery().select(UserAuth::getUserInfoId, UserAuth::getUsername, UserAuth::getLoginType).eq(UserAuth::getId, guestbook.getUserAuthId()).one();
            UserInfo userInfo = userInfoService.lambdaQuery().select(UserInfo::getNickname, UserInfo::getEmail).eq(UserInfo::getId, userAuth.getUserInfoId()).one();
            nickname = userInfo.getNickname();
            email = userInfo.getEmail();
            if (LoginTypeEmus.Email.getName().equals(userAuth.getLoginType())) {
                email = userAuth.getUsername();
            }

        } else if (UserTypeEums.Visitor.getName().equals(userType)) {
            // 游客留言
            nickname = guestbook.getNickname();
            email = guestbook.getEmail();
        } else {
            nickname = "【未知】";
            email = "【未知】";
        }
        Context context = getDefaultContext();
        context.setVariable("title", "新的留言通知已送达~");
        context.setVariable("type", userTypeName);
        context.setVariable("nickname", nickname);
        context.setVariable("email", email);
        context.setVariable("content", guestbook.getContent());
        context.setVariable("ipAddress", guestbook.getIpAddress());
        context.setVariable("ipSource", guestbook.getIpSource());
        context.setVariable("device", guestbook.getDevice());
        context.setVariable("browser", guestbook.getBrowser());
        MailDto mailDto = new MailDto(emailUtils.mailSender, "留言板上有新留言了哟~", generateHtmlStr(LEAVE_WORD_NOTICE_TEMPLATE_NAME, context));
        emailUtils.sentHtmlMail(mailDto);
    }

    /**
     * 根据配置名称，获取是否要通知
     *
     * @param configName
     * @return
     */
    private boolean isNotice(String configName) {
        // 默认开启通知
        boolean flag = true;
        List<Notice> noticeConfig = getNoticeConfig();
        if (CollectionUtils.isEmpty(noticeConfig)) {
            return flag;
        }
        for (Notice notice : noticeConfig) {
            if (notice.getName().equals(configName)) {
                return notice.getShow() == 1;
            }
        }
        return flag;
    }

    /**
     * 获取通知配置
     *
     * @return
     */
    private List<Notice> getNoticeConfig() {
        SystemConfigDto noticeConfig = systemConfigService.getNoticeConfig();
        if (ObjectUtils.isNotNull(noticeConfig)) {
            String value = noticeConfig.getValue();
            return JSON.parseArray(value, Notice.class);
        }
        return null;
    }

    /**
     * 获得默认的context，配置了默认的展示内容
     *
     * @return
     */
    private Context getDefaultContext() {
        Context context = new Context();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String datetime = now.format(formatter);
        // 设置显示时间
        context.setVariable("datetime", datetime);
        // TODO 设置显示的图标
        context.setVariable("logo", "http://");
        return context;
    }

    /**
     * 根据模板和上下文，生成HTML模板字符串
     *
     * @param templateName
     * @param context
     * @return
     */
    private String generateHtmlStr(String templateName, Context context) {
        return templateEngine.process(templateName, context);
    }
}
