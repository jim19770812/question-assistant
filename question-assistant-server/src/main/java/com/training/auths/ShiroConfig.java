package com.training.auths;

import com.common.utils.MapBuilder;
import com.training.domains.UserDomain;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Resource
    private UserDomain userDomain;

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        var result=new ShiroFilterFactoryBean();
        result.setSecurityManager(securityManager);
        //配置不会被拦截的连接，按照定义的顺序依次判断
        var filterChainMap=MapBuilder.<String, String>map()
                .put("/druid/**", "anon")
//                .put("/swagger-ui.html", "anon")
//                .put("/swagger**/**", "anon")
//                .put("/swagger**", "anon")
//                .put("/webjars/**", "anon")
//                .put("/api-docs", "anon")
                .put("/swagger-ui.html", "anon")
                .put("/swagger-resources/**", "anon")
                .put("/csrf/**", "anon")
                .put("/**/csrf", "anon")
                .put("/v2/api-docs", "anon")
                .put("/webjars/springfox-swagger-ui/**", "anon")
                .put("/**.js", "anon")
                .put("/**.png", "anon")
                .put("/**.css", "anon")
                .put("/v1/user/login", "anon")
                .put("/**", "jwt") //这条是兜底的规则，anon是不检查权限，jwt是用jwt检查权限
                .build();
        result.setFilterChainDefinitionMap(filterChainMap);

        //添加shiro过滤器
        Map<String, Filter> filterMap= MapBuilder.<String, Filter>map().put("jwt", new JwtFilter()).build();
        result.setFilters(filterMap);

        //未授权页面
        result.setUnauthorizedUrl("/403");
        return result;

    }

    @Bean
//    @ConditionalOnExpression("${service.checkPermission}")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(ShiroRealm myRealm) {
        var securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);

        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;

    }
}
