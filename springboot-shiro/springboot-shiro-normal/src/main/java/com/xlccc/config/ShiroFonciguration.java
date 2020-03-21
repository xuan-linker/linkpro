package com.xlccc.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Author Linker
 * @Date 2020/2/9 1:03
 * @Version 1.0
 */
@Configuration
public class ShiroFonciguration {
    @Bean
    public Realm realm() {
        return new AuthRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        return securityManager;
    }

    @Bean(name = "shiroFilterFactoryBean ")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);

        //1.定义URL拦截链
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/login.*", "anon");
        filterChainDefinitionMap.put("/logout*", "anon");
        filterChainDefinitionMap.put("/hello", "anon");
        filterChainDefinitionMap.put("/defaultKaptcha", "anon");
        filterChainDefinitionMap.put("/index.*", "authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        //2.配置用于登录的url和登录成功的url
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/403");

        return bean;
    }

    //url配置
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // logged in users with the 'admin' role
        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");

        // logged in users with the 'document:read' permission
        chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");

        // all other paths require a logged in user
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    /**
     * 解决spring aop和注解配置一起使用的bug。如果您在使用shiro注解配置的同时，引入了spring aop的starter，
     * 会有一个奇怪的问题，导致shiro注解的请求，不能被映射，需加入这个配置
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * Thymeleaf中使用shiro标签
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}
