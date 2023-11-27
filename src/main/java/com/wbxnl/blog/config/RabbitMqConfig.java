package com.wbxnl.blog.config;

import com.alibaba.fastjson2.JSONObject;
import com.wbxnl.blog.model.dto.MailDto;
import com.wbxnl.blog.utils.EmailUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author wansheng
 * @createDate 2023/1/17 20:58
 */
@Configuration
@EnableRabbit
public class RabbitMqConfig {
    @Autowired
    private EmailUtils emailUtils;

    public static final String EMAIL_QUEUE="blog.email";


    /**
     * 创建一个邮件队列
     */
    @Bean
    public Queue mailQueue(){
        return new Queue(EMAIL_QUEUE);//队列名称
    }

    // 配置消息监听器与队列的关联关系
    @RabbitListener(queues =EMAIL_QUEUE)
    public void processMessage(String mailJsonString, Message rabbitMessage) {
        // 手动应答
        try {

            // 在这里处理接收到的消息
            MailDto mailDto = JSONObject.parseObject(mailJsonString, MailDto.class);
            emailUtils.sentHtmlMail(mailDto);
            // 手动应答
            rabbitMessage.getMessageProperties().getDeliveryTag();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
