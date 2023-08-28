package com.wbxnl.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wbxnl.blog.common.PageData;
import com.wbxnl.blog.common.Result;
import com.wbxnl.blog.constant.SuccessMessageConstant;
import com.wbxnl.blog.constant.defaults.DefaultConstant;
import com.wbxnl.blog.constant.params.HeaderParamConstant;
import com.wbxnl.blog.constant.keys.UserPrefix;
import com.wbxnl.blog.enums.LoginTypeEmus;
import com.wbxnl.blog.enums.OperationStateCode;
import com.wbxnl.blog.exception.BlogException;
import com.wbxnl.blog.model.dto.*;
import com.wbxnl.blog.model.entity.*;
import com.wbxnl.blog.dao.UserAuthDao;
import com.wbxnl.blog.model.dto.LoginDataDto;
import com.wbxnl.blog.model.vo.*;
import com.wbxnl.blog.model.vo.params.PageParams;
import com.wbxnl.blog.model.vo.params.QueryParams;
import com.wbxnl.blog.model.vo.params.UserAuthParams;
import com.wbxnl.blog.service.*;
import com.wbxnl.blog.service.impl.base.AbstractServiceImpl;
import com.wbxnl.blog.utils.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户账号 服务实现类
 * </p>
 *
 * @author 小汍笙
 * @since 2022-08-26
 */
@Service
@Slf4j
public class UserAuthServiceImpl extends AbstractServiceImpl<UserAuthDao, UserAuth, UserAuthDto, UserAuthVo> implements UserAuthService {

    @Autowired
    private UserAuthDao userAuthDao;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    private AuthenticationManager authenticationManager;


    @Autowired
    public void setAuthenticationManager(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public PageData<UserAuthDto> getPage(PageParams page, QueryParams queryParams) {
        UserAuthParams authParams = (UserAuthParams) queryParams;
        //把参数赋值给对象
        List<UserAuthDto> userAuthList = userAuthDao.getUserList((page.getPage() - 1) * page.getLimit(), page.getLimit(), authParams);
        Long count = userAuthDao.getCount(authParams);
        PageData<UserAuthDto> pageData = new PageData<>(userAuthList, count);
        return pageData;
    }
    
    @Override
    @Transactional
    public Result login(LoginVo loginVo) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword());
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            return new Result().error(OperationStateCode.LOGIN_FAILURE);
        }
//        if (Objects.isNull(authenticate)) {
//        return new Result().error(OperationStateCode.LOGIN_FAILURE);
//        }
        UserDetailsDto userDetailsDto = (UserDetailsDto) authenticate.getPrincipal();
        //如果认证通过了，使用userid生成一个jwt jwt存入Result返回
        UserDto userDto = userDetailsDto.getUserDto();
        //更新用户登录信息
        String ipAddress = IPUtils.getIpAddress(request);
        String ipSource = IPUtils.getIpSource(ipAddress);
        String deviceType = UserAgentUtils.getOsName(request);
        String browserName = UserAgentUtils.getBrowserName(request);
        LoginLog loginLog = new LoginLog(null, userDto.getUserAuthId(), ipAddress, ipSource, deviceType, browserName, null, null, null, null, null);
        loginLogService.save(loginLog);
        log.info("更新了用户{}登录时间：", userDto.getUsername());

//        String token = JwtUtil.createJWT(userAuthVo.getUsername());
        String token = JwtUtil2.getToken(userDto.getUsername(), new HashMap<>());
        String refreshToken = JwtUtil2.getRefreshToken(userDto.getUsername(), new HashMap<>());
        log.info("获取了token：" + token);
        //把用户信息存入redis
//        redisUtils.setCacheObject("login:"+userAuthVo.getUsername(),userDetailsDto);
        String key = UserPrefix.USER_INFO + userDto.getUsername();
        UserDto oldUserDto = redisUtils.getCacheObject(key);
        if (oldUserDto != null) {
            //登录设备数+1
            userDto.setCount(oldUserDto.getCount() + 1);
        } else {
            userDto.setCount(1);
        }
        //有效期和refresh_token的过期时间相同
        redisUtils.setCacheObject(key, userDto, JwtUtil2.REFRESH_EXPIRE_TIME, TimeUnit.MILLISECONDS);
        log.info("保存用户数据到redis。");

        LoginDataDto loginDataDto = new LoginDataDto(
                userDetailsDto.getUsername(),
                token,
                JwtUtil2.getExpireTime(token),
                refreshToken,
                userDto.getRoles()
                        .stream()
                        .map(roleDto -> roleDto.getName())
                        .collect(Collectors.toList())
        );

        //  邮件通知作者
        emailService.loginNotice(userDto,ipAddress,ipSource,deviceType,browserName);

//        response.setHeader("Access-Control-Expose-Headers", token);
        return new Result().ok(SuccessMessageConstant.LOGIN, loginDataDto);

    }

    @Override
    public Result logout() {
        //获取token 解析获取userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsDto userDetailsDto = (UserDetailsDto) authentication.getPrincipal();
        //获取userid
        String username = userDetailsDto.getUserDto().getUsername();
        String key = UserPrefix.USER_INFO + username;
        UserDto userDto = redisUtils.getCacheObject(key);
        log.info("用户{}注销", username);
        if (userDto == null) {
            return new Result();
        }
        log.info("用户{}登录数{}。", username, userDto.getCount());
        if (userDto.getCount() > 1) {
            //登录设备减一
            userDto.setCount(userDto.getCount() - 1);
            long expire = redisUtils.getExpire(key, TimeUnit.SECONDS);
            log.info("过期时间还剩：" + expire);
            redisUtils.setCacheObject(key, userDto, expire, TimeUnit.SECONDS);
        } else {
            //删除redis中的用户信息
            redisUtils.deleteObject(key);
        }
        return new Result<>();
    }

    @Override
    @Transactional
    public Result signup(SignupVo signupVo) {
        String email = signupVo.getEmail();
        String verificationCode = signupVo.getVerificationCode();
        String password = signupVo.getPassword();
        String key = UserPrefix.VERIFICATION_CODE + email;
        String code = redisUtils.getCacheObject(key);
//      忽略大小写
        if (StringUtils.isNotBlank(code) && code.compareToIgnoreCase(verificationCode) == 0) {
            // 验证码使用完随即销毁
            redisUtils.deleteObject(key);
            UserAuth one = lambdaQuery().select(UserAuth::getId).eq(UserAuth::getUsername, email).one();
            if (ObjectUtils.isNotNull(one)) {
                throw new BlogException(OperationStateCode.ACCOUNT_EXIST);
            }
            String ipAddress = IPUtils.getIpAddress(request);
            String ipSource = IPUtils.getIpSource(ipAddress);
            UserAuth userAuth = new UserAuth()
                    .setUsername(email)
                    .setLoginType(LoginTypeEmus.Email.getName())
                    .setPassword(passwordEncoder.encode(password))
                    .setIpAddressSignup(ipAddress)
                    .setIpSourceSignup(ipSource);

            UserInfoVo userInfoVo = new UserInfoVo()
                    .setEmail(email)
                    .setNickname(signupVo.getNickname());
            // 保存用户信息
            UserInfo userInfo = userInfoService.saveVo(userInfoVo);
            userAuth.setUserInfoId(userInfo.getId());
            // 保存用户账户
            save(userAuth);
            Role role = roleService.lambdaQuery().select(Role::getId).eq(Role::getName, DefaultConstant.USER_ROLE).one();
            if (role == null) {
                log.info("系统无默认用户角色：{}", DefaultConstant.USER_ROLE);
                throw new BlogException(OperationStateCode.GRANT_ROLE_FAILURE);
            }
            UserRole userRole = UserRole.builder().roleId(role.getId()).userAuthId(userAuth.getId()).build();
            // 保存用户角色信息
            userRoleService.save(userRole);

            //通知作者
            emailService.registerNotice(email,signupVo.getNickname(),ipAddress,ipSource);
            return new Result().ok(SuccessMessageConstant.SIGNUP);
        } else {
            throw new BlogException(OperationStateCode.VERIFICATION_ERROR);
        }
    }

    @Override
    public String getVerificationCode(String email) {

        String code = StrUtils.getVerificationCode(6);
        log.info("邮箱：[{}]，获取的验证码：[{}]", email, code);
        //定时十五分钟
        redisUtils.setCacheObject(UserPrefix.VERIFICATION_CODE + email, code, 15L, TimeUnit.MINUTES);
        boolean flag = emailService.sendVerification(email, code);
        if (!flag) {
            throw new BlogException(OperationStateCode.SEND_CODE_FAILURE);
        }
        return code;
    }

    @Override
    public Result resetPassword(ResetVo resetVo) {
        String key = UserPrefix.VERIFICATION_CODE + resetVo.getEmail();
        String code = redisUtils.getCacheObject(key);
//      忽略大小写
        if (StringUtils.isNotBlank(code) && code.compareToIgnoreCase(resetVo.getVerificationCode()) == 0) {
            // 验证码使用后即销毁
            redisUtils.deleteObject(key);
            UserAuth userAuth = lambdaQuery().select(UserAuth::getId).eq(UserAuth::getUsername, resetVo.getEmail()).one();
            if (ObjectUtils.isNull(userAuth)) {
                return new Result().error(OperationStateCode.ACCOUNT_NOT_EXIST);
            }
            userAuth.setPassword(passwordEncoder.encode(resetVo.getPassword()));
            updateById(userAuth);
            return new Result().ok(SuccessMessageConstant.RESET);
        } else {
            throw new BlogException(OperationStateCode.VERIFICATION_ERROR);
        }
    }

    @Override
    public Result refreshToken(HttpServletRequest request) {
        //获取刷新token
        String refreshToken = request.getHeader(HeaderParamConstant.HEADER_TOKEN);
        String username = JwtUtil2.getUserId(refreshToken);
        Map<String, Object> information = new HashMap<>();
        String token = JwtUtil2.getToken(username, information);
        String key = UserPrefix.USER_INFO + username;
        UserDto useDto = redisUtils.getCacheObject(key);
        UserAuth userAuth = lambdaQuery().select(UserAuth::getId, UserAuth::getDisable)
                .eq(UserAuth::getUsername, username).one();
        if (userAuth.getDisable() == 1) {
            //已被禁用
            throw new BlogException(OperationStateCode.USER_DISABLE);
        }

        UserRoleDto userRoles = userRoleService.getUserRoles(userAuth.getId());
        if (Objects.nonNull(userRoles)) {
            List<RoleDto> roleDtos = userRoles.getRolesList()
                    .stream()
                    .filter(roleDto -> roleDto.getDisable() == 0)
                    .collect(Collectors.toList());
            useDto.setRoles(roleDtos);
        } else {
            useDto.setRoles(new ArrayList<>(0));
        }
        long expire = redisUtils.getExpire(key, TimeUnit.SECONDS);
        redisUtils.setCacheObject(key, useDto, expire, TimeUnit.SECONDS);
        //返回新token
        LoginDataDto loginDataDto = new LoginDataDto();
        loginDataDto.setAccessToken(token);
        loginDataDto.setExpire(JwtUtil2.getExpireTime(token));
        return new Result<>().ok(loginDataDto);
    }

    @Override
    public void updatePassword(PasswordVo passwordVo) {
        UserAuth one = lambdaQuery()
                .select(UserAuth::getPassword)
                .eq(UserAuth::getUsername, SecurityUtils.getUsername())
                .one();
        boolean matches = passwordEncoder.matches(passwordVo.getOldPassword(), one.getPassword());
        if (!matches) {
            throw new BlogException(OperationStateCode.PASSWORD_ERROR);
        }
        lambdaUpdate()
                .set(UserAuth::getPassword, passwordEncoder.encode(passwordVo.getNewPassword()))
                .eq(UserAuth::getId, SecurityUtils.getUserAuthId())
                .update();
    }

    @Override
    @Transactional
    public UserAuth saveVo(UserAuthVo userAuthVo) {
        UserInfoVo userInfoVo = userAuthVo.getUserInfo();
        UserInfo userInfo = userInfoService.saveVo(userInfoVo);
        userAuthVo.setPassword(passwordEncoder.encode(userAuthVo.getPassword()));
        UserAuth userAuth = ConvertUtils.sourceToTarget(userAuthVo, UserAuth.class);
        //默认账号和密码登录
        String ipAddress = IPUtils.getIpAddress(request);
        String ipSource = IPUtils.getIpSource(ipAddress);
        userAuth
                .setLoginType(LoginTypeEmus.Email.getName())
                .setUserInfoId(userInfo.getId())
                .setIpAddressSignup(ipAddress)
                .setIpSourceSignup(ipSource);
        save(userAuth);
        if (CollectionUtils.isNotEmpty(userAuthVo.getRoleIds())) {
            List<UserRole> roleList = userAuthVo.getRoleIds().stream()
                    .map(roleId -> new UserRole().setUserAuthId(userAuth.getId()).setRoleId(roleId))
                    .collect(Collectors.toList());
            userRoleService.saveBatch(roleList,roleList.size());
        }
        return userAuth;
    }

    @Override
    @Transactional
    public void update(UserAuthVo userAuthVo) {
        UserInfoVo userInfoVo = userAuthVo.getUserInfo();
        if (ObjectUtils.isNotNull(userInfoVo) && ObjectUtils.isNotNull(userInfoVo.getId())) {
            userInfoService.update(userInfoVo);
        }
        List<Integer> roleIds = userAuthVo.getRoleIds();
        if (CollectionUtils.isNotEmpty(roleIds)) {
            userRoleService.lambdaUpdate().eq(UserRole::getUserAuthId, userAuthVo.getId()).remove();
            for (Integer roleId : roleIds) {
                userRoleService.save(UserRole.builder().userAuthId(userAuthVo.getId()).roleId(roleId).build());
            }
        }
        UserAuth userAuth = ConvertUtils.sourceToTarget(userAuthVo, UserAuth.class);
        updateById(userAuth);
    }
}
