package com.xlccc.shiro;

import com.xlccc.dao.ShiroSampleDao;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author Linker
 * @Date 2020/2/8 22:26
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private ShiroSampleDao shiroSampleDao;

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = this.shiroSampleDao.getPasswordByUsername(username);
        return new SimpleAuthenticationInfo(username, password, getName());
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) super.getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = shiroSampleDao.getRolesByUsername(username);
        authorizationInfo.setRoles(roles);
        roles.forEach(role -> {
            Set<String> permissions = this.shiroSampleDao.getPermissionsByRole(role);
            authorizationInfo.addStringPermissions(permissions);
        });
        return authorizationInfo;
    }
}
