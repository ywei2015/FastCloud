package com.demo.gateway.sso;

import com.bonc.sso.client.SSOFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class CasConfig {

    private static boolean casEnabled  = true;
    @Autowired
    private SpringCasAutoconfig springCasAutoconfig;

    @Bean
    public FilterRegistrationBean authenticationFilterRegistrationBean() {
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new SSOFilter());  
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("serverName", springCasAutoconfig.getServerName());
        initParameters.put("casServerUrlPrefix", springCasAutoconfig.getCasServerUrlPrefix());  
        initParameters.put("casServerLoginUrl", springCasAutoconfig.getCasServerLoginUrl());
        initParameters.put("singleSignOut", springCasAutoconfig.getSingleSignOut());
        initParameters.put("loginUserHandle", springCasAutoconfig.getLoginUserHandle());
        initParameters.put("characterEncoding", springCasAutoconfig.getCharacterEncoding());
        initParameters.put("encoding", springCasAutoconfig.getEncoding());
        initParameters.put("skipUrls", springCasAutoconfig.getSkipUrls());
        authenticationFilter.setInitParameters(initParameters);  
        authenticationFilter.setOrder(2);  
        List<String> urlPatterns = new ArrayList<String>();
        // 设置匹配的url,拦截/*的时部分js插件无法正常使用，必须改源码 给所有ajax加上凭证。
        urlPatterns.add("/**");
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;  
    }  
}