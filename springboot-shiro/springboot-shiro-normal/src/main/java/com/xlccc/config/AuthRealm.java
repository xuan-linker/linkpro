package com.xlccc.config;

import com.xlccc.pojo.User;
import com.xlccc.service.impl.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author Linker
 * @Date 2020/2/9 1:03
 * @Version 1.0
 */
public class AuthRealm extends AuthorizingRealm {
    @Resource
    private UserServiceImpl userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取principalCollection中的用户
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        // 2.通过数据库查询当前user的权限
        List<String> permissions = userService.selectPermissionByUserId(user.getUid());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //3.将权限放入shiro中
        simpleAuthorizationInfo.addStringPermissions(permissions);
        //4.返回
        return simpleAuthorizationInfo;
    }

    /**
     * 完成身份认证并返回认证信息
     * 认证失败则返回空
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1.用户输入的token
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        User user = userService.findUserByName(username);
        System.out.println("获取到的密码" + user.getUpwd());
        // 2.返回
        return new SimpleAuthenticationInfo(user, user.getUpwd(), this.getClass().getName());
    }
}
