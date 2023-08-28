package com.wbxnl.blog.config;

import com.wbxnl.blog.filter.JwtAuthenticationTokenFilter;
import com.wbxnl.blog.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author wansheng
 * @createDate 2022/8/27 5:20
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    /**
     * @return
     */

    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        return authenticationManager;
    }

    /**
     * 创建一个加密处理实例
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,AuthorizationManager<RequestAuthorizationContext> authz) throws Exception {
        httpSecurity.authenticationManager(authenticationManager());
        //配置登录和注销路径
        httpSecurity
                // 跨站保护保护
                .csrf(httpSecurityCsrfConfigurer -> {
                    httpSecurityCsrfConfigurer.disable();
                })
//                .csrf(Customizer.withDefaults())
                // 跨域资源共享
                .cors(Customizer.withDefaults())
                .formLogin(new Customizer<FormLoginConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(FormLoginConfigurer<HttpSecurity> httpSecurityFormLoginConfigurer) {
                        httpSecurityFormLoginConfigurer.disable();
                    }
                });
        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.authorizeHttpRequests(authorizeHttpRequests -> {
                    authorizeHttpRequests
                            .requestMatchers(HttpMethod.OPTIONS).permitAll()
                            .requestMatchers("/**/login").anonymous()
                            .requestMatchers("/**/logout").authenticated()
                            // 使用自定义授权
                            .anyRequest().access(authz);
                })
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                    httpSecurityExceptionHandlingConfigurer
                            //未登录处理
                            .authenticationEntryPoint(authenticationEntryPoint)
                            //权限不足处理器
                            //用来解决认证过的用户访问无权限资源时的异常
                            .accessDeniedHandler(accessDeniedHandler);
                })
                .sessionManagement(httpSecuritySessionManagementConfigurer -> {
//                //不通过Session获取SecurityContext
                    httpSecuritySessionManagementConfigurer.disable();
                });
        //把jwtAuthenticationTokenFilter添加到SpringSecurity的过滤器链中
        // 自动注入了
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
