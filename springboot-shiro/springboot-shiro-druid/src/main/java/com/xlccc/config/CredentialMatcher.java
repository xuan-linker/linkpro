package com.xlccc.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @Author Linker
 * @Date 2020/3/27 8:56 下午
 * @Version 1.0
 * @Todo:自定义matcher,这里就是对比用户的输入的信息封装成的token和按照用户输入的principal(一般就是用户名)从数据库中查询出的信息封装的info信息,一般就是比对他们的Credentials
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String password = new String(usernamePasswordToken.getPassword());
        String dbPassword = (String) info.getCredentials();
        return this.equals(password, dbPassword);
    }
}
