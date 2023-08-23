package com.wbxnl.blog.utils;

import com.wbxnl.blog.model.dto.MailDto;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wansheng
 * @createDate 2023/1/16 20:37
 */
@Component
@Slf4j
public class EmailUtils {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    public String mailSender;//邮件发送者

    /**
     * 发送文本邮件
     *
     * @param mailDto 邮件实体
     * @return
     */
    public boolean sentTextMail(MailDto mailDto) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(mailSender);//发送者
            mailMessage.setTo(mailDto.getReceiver());//接收者
            mailMessage.setSubject(mailDto.getSubject());//邮件主题
            mailMessage.setText(mailDto.getContent());//邮件文字内容
            mailMessage.setSentDate(new Date());//发送时间
            javaMailSender.send(mailMessage);//发送
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 发送HTML类型邮件
     *
     * @param mailDto 邮件实体
     * @return
     */
    public boolean sentHtmlMail(MailDto mailDto) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(mailSender);//发送者
            messageHelper.setTo(mailDto.getReceiver());//接收者
            messageHelper.setSubject(mailDto.getSubject());//邮件主题
            messageHelper.setText(mailDto.getContent(),true);//邮件文字内容
            messageHelper.setSentDate(new Date());//发送时间
            javaMailSender.send(mimeMessage);//发送
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
