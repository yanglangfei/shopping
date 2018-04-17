package com.yf.lib.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class DruidConfig {

    @Value("${define.druid.username}")
    private String username;
    @Value("${define.druid.password}")
    private String password;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName","USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName","USER_SESSION");
        filterRegistrationBean.addInitParameter("DruidWebStatFilter","/*");
        return filterRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        //IP白名单 (没有配置或者为空，则允许所有访问)
        reg.addInitParameter("allow", "");
        //IP黑名单 (存在共同时，deny优先于allow)
        reg.addInitParameter("deny", "");
        reg.addInitParameter("loginUsername", username);
        reg.addInitParameter("loginPassword", password);
//        reg.addInitParameter("resetEnable", "false");
        return reg;
    }
}
