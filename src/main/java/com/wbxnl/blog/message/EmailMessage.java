package com.wbxnl.blog.message;

import com.alibaba.fastjson2.JSON;
import com.wbxnl.blog.config.RabbitMqConfig;
import com.wbxnl.blog.model.dto.MailDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author xiaowansheng
 * @Date 2023/11/27 20:35
 */
@Service
public class EmailMessage {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到MQ
     * @param message
     */
    public void sendEmailMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.EMAIL_QUEUE, message);
    }

    /**
     * 发送邮件消息到MQ
     * @param mailDto
     */
    public void sendEmailMessage(MailDto mailDto) {
        String jsonString = JSON.toJSONString(mailDto);
        sendEmailMessage(jsonString);
    }

}
