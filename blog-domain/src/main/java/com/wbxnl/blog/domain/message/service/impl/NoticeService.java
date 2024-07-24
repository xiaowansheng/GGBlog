package com.wbxnl.blog.domain.message.service.impl;

import com.wbxnl.blog.common.enums.TopicTypeEnum;
import com.wbxnl.blog.domain.message.model.aggregate.*;
import com.wbxnl.blog.domain.message.model.entity.*;
import com.wbxnl.blog.domain.message.service.IEmailService;
import com.wbxnl.blog.domain.message.service.INoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.wbxnl.blog.common.constant.HtmlTemplateConstant.*;

/**
 * description: 略
 * TODO 在触发层使用通知服务，进行通知
 * TODO 可以使用MQ解耦，以及异步进行消息处理和防止消息丢失
 * TODO 使用自定义注解加aop，对通知进行拦截，通过配置判断是否发送通知
 *
 * @author xiaowansheng
 * @since 2024/7/23 14:51
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeService implements INoticeService {

    private final IEmailService emailService;

    private final TemplateEngine templateEngine;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获得默认的context，配置了默认的展示内容
     *
     * @return Context
     */
    private Context getDefaultContext() {
        Context context = new Context();
        LocalDateTime now = LocalDateTime.now();
        String datetime = now.format(FORMATTER);
        // 设置显示时间
        context.setVariable("datetime", datetime);
        // 设置显示的图标
//        context.setVariable("logo", logo);
        return context;
    }

    /**
     * 根据模板和上下文，生成HTML模板字符串
     *
     * @param templateName html模板名称
     * @param context      上下文
     * @return 模板字符串
     */
    private String generateHtmlStr(String templateName, Context context) {
        return templateEngine.process(templateName, context);
    }


    @Override
    public void sendCaptchaNotice(CaptchaNoticeAggregate captchaNoticeAggregate) {
        Context context = getDefaultContext();
        context.setVariable("title", "嗨,您的验证码已送达~");
        context.setVariable("code", captchaNoticeAggregate.getCode());
        // 设置使用期限展示
        context.setVariable("expire", 15);
        String htmlStr = generateHtmlStr(VERIFICATION_TEMPLATE_NAME, context);
        EmailEntity mailEntity = new EmailEntity(captchaNoticeAggregate.getEmail(), "系统验证码", htmlStr);
        emailService.sentHtmlMail(mailEntity);
    }

    @Override
    public void sendRegisterNotice(RegisterNoticeAggregate registerNoticeAggregate) {
        Context context = getDefaultContext();
        context.setVariable("title", "嘿，你的网站又有了新的用户哦~");
        context.setVariable("email", registerNoticeAggregate.getEmail());
        context.setVariable("nickname", registerNoticeAggregate.getNickname());
        context.setVariable("ipAddress", registerNoticeAggregate.getIpAddress());
        context.setVariable("ipSource", registerNoticeAggregate.getIpSource());
        String htmlStr = generateHtmlStr(REGISTER_NOTICE_TEMPLATE_NAME, context);
        EmailEntity emailEntity = new EmailEntity(registerNoticeAggregate.getEmail(), "网站新用户注册通知~", htmlStr);
        emailService.sentHtmlMail(emailEntity);
    }

    @Override
    public void sendLoginNotice(LoginNoticeAggregate loginNoticeAggregate) {
        Context context = getDefaultContext();
        String username = loginNoticeAggregate.getUsername();
        context.setVariable("title", "用户登录通知已送达~");
        context.setVariable("username", username);
        context.setVariable("ipAddress", loginNoticeAggregate.getIpAddress());
        context.setVariable("ipSource", loginNoticeAggregate.getIpSource());
        context.setVariable("device", loginNoticeAggregate.getDevice());
        context.setVariable("browser", loginNoticeAggregate.getBrowser());
        EmailEntity emailEntity = new EmailEntity(loginNoticeAggregate.getEmail(), "有用户登录网站了哦", generateHtmlStr(LOGIN_NOTICE_TEMPLATE_NAME, context));
        emailService.sentHtmlMail(emailEntity);
    }

    @Override
    public void sendLeaveWordNotice(LeaveWordNoticeAggregate leaveWordNoticeAggregate) {
        Context context = getDefaultContext();
        context.setVariable("title", "新的留言通知已送达~");
        context.setVariable("type", leaveWordNoticeAggregate.getUserType());
        context.setVariable("nickname", leaveWordNoticeAggregate.getNickname());
        context.setVariable("email", leaveWordNoticeAggregate.getEmail());
        context.setVariable("content", leaveWordNoticeAggregate.getContent());
        context.setVariable("ipAddress", leaveWordNoticeAggregate.getIpAddress());
        context.setVariable("ipSource", leaveWordNoticeAggregate.getIpSource());
        context.setVariable("device", leaveWordNoticeAggregate.getDevice());
        context.setVariable("browser", leaveWordNoticeAggregate.getBrowser());
        EmailEntity emailEntity = new EmailEntity(leaveWordNoticeAggregate.getEmail(), "留言板上有新留言了哟~", generateHtmlStr(LEAVE_WORD_NOTICE_TEMPLATE_NAME, context));
        emailService.sentHtmlMail(emailEntity);
    }

    @Override
    public void sendCommentNotice(CommentNoticeAggregate commentNoticeAggregate) {
        /*
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
        Context context = getDefaultContext();
        EmailEntity emailEntity = null;
        // TODO 获取话题的链接和名称
        String topicKey = commentNoticeAggregate.getTopicKey();
        if(StringUtils.hasText(topicKey)){
            TopicTypeEnum topicType = commentNoticeAggregate.getTopicType();
            String topicName = "UNKNOW";
            context.setVariable("topicType", topicType.getName());
            context.setVariable("topicName", topicName);
        }
        String replyCommentKey = commentNoticeAggregate.getReplyCommentKey();
        // 判断是评论网站内容，还是是回复评论
        if(StringUtils.hasText(replyCommentKey)){
            // 回复其他人的评论
            context.setVariable("title", "新的评论信息已送达~");
            // TODO 处理话题类型
            context.setVariable("topicName", "UNKNOW");
            // TODO 处理url
            context.setVariable("url","UNKNOW");
//            context.setVariable("nickname", commentNoticeEntity.getNickname());
//            context.setVariable("ipAddress", commentNoticeEntity.getIpAddress());
//            context.setVariable("ipSource", commentNoticeEntity.getIpAddress());
//            context.setVariable("device", commentNoticeEntity.getDevice());
//            context.setVariable("browser", commentNoticeEntity.getBrowser());
            context.setVariable("comment", commentNoticeAggregate.getContent());
            // TODO 普通评论模板
            emailEntity = new EmailEntity(
                    commentNoticeAggregate.getEmail(),
                    "您的消息被小伙伴回复了哟~",
                    generateHtmlStr(COMMENT_NOTICE_TEMPLATE_NAME, context));
        }else{
            // 评论网站内容
            context.setVariable("title", "新的评论信息已送达~");
            // TODO 处理话题类型
            context.setVariable("topicName", "UNKNOW");
            // TODO 处理url
            context.setVariable("url","UNKNOW");
            context.setVariable("nickname", commentNoticeAggregate.getNickname());
            context.setVariable("ipAddress", commentNoticeAggregate.getIpAddress());
            context.setVariable("ipSource", commentNoticeAggregate.getIpAddress());
            context.setVariable("device", commentNoticeAggregate.getDevice());
            context.setVariable("browser", commentNoticeAggregate.getBrowser());
            context.setVariable("comment", commentNoticeAggregate.getContent());

            // TODO 站长通知模板
            emailEntity = new EmailEntity(
                    commentNoticeAggregate.getEmail(),
                    "网站有新的评论信息~",
                    generateHtmlStr(COMMENT_NOTICE_TEMPLATE_NAME, context));
        }

        emailService.sentHtmlMail(emailEntity);
    }
}
