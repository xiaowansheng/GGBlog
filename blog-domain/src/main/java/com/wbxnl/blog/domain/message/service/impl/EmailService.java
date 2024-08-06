package com.wbxnl.blog.domain.message.service.impl;

import com.wbxnl.blog.common.enums.OperationCodeEnum;
import com.wbxnl.blog.common.exception.BlogException;
import com.wbxnl.blog.domain.message.model.entity.EmailEntity;
import com.wbxnl.blog.domain.message.service.IEmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * description: 略
 *
 * @author xiaowansheng
 * @since 2024/7/23 11:17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {

    private final JavaMailSender javaMailSender;

    /**
     * 邮件发送者
     */
    @Value("${spring.mail.username}")
    public String mailSender;

    @Override
    public void sentTextMail(EmailEntity emailEntity) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            //发送者
            mailMessage.setFrom(mailSender);
            //接收者
            mailMessage.setTo(emailEntity.getReceiver());
            //邮件主题
            mailMessage.setSubject(emailEntity.getSubject());
            //邮件文字内容
            mailMessage.setText(emailEntity.getContent());
            //发送时间
            mailMessage.setSentDate(new Date());
            //发送
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            log.error("邮件发送失败!", e);
            throw new BlogException(OperationCodeEnum.SEND_FAILURE);
        }
    }


    @Override
    public void sentHtmlMail(EmailEntity emailEntity) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            //发送者
            messageHelper.setFrom(mailSender);
            //接收者
            messageHelper.setTo(emailEntity.getReceiver());
            //邮件主题
            messageHelper.setSubject(emailEntity.getSubject());
            //邮件文字内容
            messageHelper.setText(emailEntity.getContent(), true);
            //发送时间
            messageHelper.setSentDate(new Date());
            //发送
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("邮件发送失败!", e);
            throw new BlogException(OperationCodeEnum.SEND_FAILURE);
        }
    }
}
