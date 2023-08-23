package com.wbxnl.blog.aspect;

import com.alibaba.fastjson2.JSON;
import com.wbxnl.blog.annotation.Log;
import com.wbxnl.blog.model.dto.UserDto;
import com.wbxnl.blog.model.entity.LogError;
import com.wbxnl.blog.model.entity.LogOperation;
import com.wbxnl.blog.service.LogErrorService;
import com.wbxnl.blog.service.LogOperationService;
import com.wbxnl.blog.utils.IPUtils;
import com.wbxnl.blog.utils.SecurityUtils;
////import io.swagger.annotations.Api;
import com.wbxnl.blog.utils.UserAgentUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 切面处理类，操作日志异常日志记录处理
 *
 * @author wansheng
 * @createDate 2022/8/27 0:52
 */
@Aspect
@Component
public class LogAspect {

    /**
     * 统计请求的处理时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    /**
     * 操作版本号
     * 项目启动时从命令行传入，例如：java -jar xxx.war --version=201902
     */
    @Value("${config.version}")
    private String version;

    @Autowired
    private LogOperationService logInfoService;

    @Autowired
    private LogErrorService logErrorInfoService;

    /**
     * @methodName：logPoinCut
     * @description：设置操作日志切入点 记录操作日志 在注解的位置切入代码
     * @author：tanyp
     * @dateTime：2021/11/18 14:22
     * @Params： []
     * @Return： void
     * @editNote：
     */
    @Pointcut("@annotation(com.wbxnl.blog.annotation.Log)")
    public void logPoinCut() {
    }

    /**
     * @methodName：exceptionLogPoinCut
     * @description：设置操作异常切入点记录异常日志 扫描所有controller包下操作
     * @author：tanyp
     * @dateTime：2021/11/18 14:22
     * @Params： []
     * @Return： void
     * @editNote：
     */
    @Pointcut("execution(* com.wbxnl.blog.controller..*.*(..))")
    public void exceptionLogPoinCut() {
    }

    @Before("logPoinCut()")
    public void doBefore() {
        // 接收到请求，记录请求开始时间
        startTime.set(System.currentTimeMillis());
    }

    /**
     * @methodName：doAfterReturning
     * @description：正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     * @author：tanyp
     * @dateTime：2021/11/18 14:21
     * @Params： [joinPoint, keys]
     * @Return： void
     * @editNote：
     */
    @AfterReturning(value = "logPoinCut()", returning = "keys")
    public void doAfterReturning(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        LogOperation logInfo = LogOperation.builder().build();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();

            // 获取切入点所在的方法
            Method method = signature.getMethod();

            // 获取操作(根据方法所在类的@Api注解)
//            Api api = (Api) signature.getDeclaringType().getAnnotation(Api.class);//通过声明类获取api
//            Api api = (Api) joinPoint.getTarget().getClass().getAnnotation(Api.class);//通过切入的目标方法所在类获取api
            Tag api = joinPoint.getTarget().getClass().getAnnotation(Tag.class);//通过切入的目标方法所在类获取api
            String module = "【未知模块】";
            if (Objects.nonNull(api)) {
//                module = api.name();
                module = api.description();
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();

            // 获取操作
            Log log = method.getAnnotation(Log.class);
            if (Objects.nonNull(log)) {
                logInfo.setType(log.type()); //方法类型
                logInfo.setDescription(log.desc());// 方法描述
            }

//            logInfo.setId(UUID.randomUUID().toString());
//            logInfo.setRequestParam(JSON.toJSONString(converMap(request.getParameterMap()))); // 请求参数

//            logInfo.setUserAuthId(SecurityUtils.getUser().getId()); // 请求用户ID
//            logInfo.setUserName(SecurityUserUtils.getUser().getUsername()); // 请求用户名称
            // xTODO 根据登录用户
            UserDto userDto = null;
            try {
                userDto = SecurityUtils.getUserDto();
            } catch (Exception ex) {
            }
            Integer id = 0;
            String name = "【匿名用户】";
            if (userDto != null) {
                id = userDto.getUserAuthId();
                name = userDto.getUsername();
            }
            logInfo.setUserAuthId(id);
            logInfo.setUserName(name);
            logInfo.setVersion(version); // 操作版本
            // 请求方式
            logInfo.setRequestUrl(request.getRequestURI());// 请求URI
            logInfo.setRequestMethod(Objects.requireNonNull(request).getMethod());// 请求方法
            logInfo.setRequestParam(JSON.toJSONString(joinPoint.getArgs())); // 请求参数
//            logInfo.setModule(api.tags()[0]);
            logInfo.setModule(module); //访问模块
            logInfo.setCallingMethod(className + "." + method.getName()); // 请求的方法名

            logInfo.setResponseData(JSON.toJSONString(keys)); // 返回结果

            logInfo.setElapsedTime(System.currentTimeMillis() - startTime.get()); // 耗时

            logInfo.setIpAddress(IPUtils.getIpAddress(request)); // 请求IP
            logInfo.setIpSource(IPUtils.getIpSource(IPUtils.getIpAddress(request)));//请求的ip所在地

            logInfo.setDevice(UserAgentUtils.getOsName(request));// 获取设备名称
            logInfo.setBrowser(UserAgentUtils.getBrowserName(request)); // 获取浏览器名称

            logInfoService.save(logInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @methodName：doAfterThrowing
     * @description：异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     * @author：tanyp
     * @dateTime：2021/11/18 14:23
     * @Params： [joinPoint, e]
     * @Return： void
     * @editNote：
     */
    @AfterThrowing(pointcut = "exceptionLogPoinCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();

            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();

            // 获取注解
//            Api api = (Api) joinPoint.getTarget().getClass().getAnnotation(Api.class);//通过切入的目标方法所在类获取api
            Tag api = (Tag) joinPoint.getTarget().getClass().getAnnotation(Tag.class);//通过切入的目标方法所在类获取api
            String module = "【未知模块】";
            if (Objects.nonNull(api)) {
//                module = api.name();
                module = api.description();
            }

            UserDto userDto = null;
            try {
                userDto = SecurityUtils.getUserDto();
            } catch (Exception ex) {
                userDto = null;
            }
            Integer userAuthId = 0;
            String name = "【游客】";
            if (Objects.nonNull(userDto)) {
                name = userDto.getUsername();
                userAuthId = userDto.getUserAuthId();
            }
            LogError logError = LogError.builder()
//                            .id(UUID.randomUUID().toString())
                    .userAuthId(userAuthId)// 操作员ID
                    .userName(name)// 操作员名称
                    .version(version) // 版本号
                    .requestUrl(request.getRequestURI()) // 请求地址
                    .requestMethod(Objects.requireNonNull(request).getMethod())
                    .requestParam(JSON.toJSONString(joinPoint.getArgs())) // 请求参数
                    .module(module)// 访问模块
                    .callingMethod(className + "." + method.getName()) // 请求方法
                    .errorName(e.getClass().getName()) // 异常名称
                    .errorMessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace())) // 异常信息
                    .ipAddress(IPUtils.getIpAddress(request)) // 操作员IP
                    .ipSource(IPUtils.getIpSource(IPUtils.getIpAddress(request)))
                    .device(UserAgentUtils.getOsName(request))
                    .browser(UserAgentUtils.getBrowserName(request))
                    .build();
            logErrorInfoService.save(logError);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /**
     * @methodName：converMap
     * @description：转换request 请求参数
     * @author：tanyp
     * @dateTime：2021/11/18 14:12
     * @Params： [paramMap]
     * @Return： java.util.Map<java.lang.String, java.lang.String>
     * @editNote：
     */
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    /**
     * @methodName：stackTraceToString
     * @description：转换异常信息为字符串
     * @author：tanyp
     * @dateTime：2021/11/18 14:12
     * @Params： [exceptionName, exceptionMessage, elements]
     * @Return： java.lang.String
     * @editNote：
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "<br/>");
        }
        String message = exceptionName + ":" + exceptionMessage + "<br/>" + strbuff.toString();
        return message;
    }
}


