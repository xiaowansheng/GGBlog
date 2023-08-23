package com.wbxnl.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * 配置 HTML 模板解析器
 * @Author xiaowansheng
 * @Date 2023/8/17 11:12
 */
@Configuration
public class EmailTemplateConfig {
    @Autowired
    private ThymeleafProperties properties;

    /**
     * 创建邮件模板解析引擎
     * @return
     */
    @Bean
    public TemplateEngine emailTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(emailTemplateResolver());
        return templateEngine;
    }

    /**
     * 设置模板加载配置：
     *      设置模板的位置，及其解析的前后缀
     * @return
     */
    private ClassLoaderTemplateResolver emailTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(properties.getMode());
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }
}
