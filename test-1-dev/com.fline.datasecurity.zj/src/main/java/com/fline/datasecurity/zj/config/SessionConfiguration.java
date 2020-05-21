package com.fline.datasecurity.zj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fline.datasecurity.zj.intercept.ServiceInterceptor;
import com.fline.datasecurity.zj.intercept.SessionInterceptor;

/**
 * session 拦截处理的配置
 * 
 * @author zhuzl
 *
 */
@Configuration
public class SessionConfiguration extends WebMvcConfigurationSupport {
	@Autowired
	SessionInterceptor sessionInterceptor;
	@Autowired
	ServiceInterceptor serviceInterceptor;

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		// 设置拦截的路径、不拦截的路径、优先级等等
		registry.addInterceptor(serviceInterceptor).addPathPatterns("/**").order(1);
		registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").order(2);
	}

}
