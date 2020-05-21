package com.fline.datasecurity.zj.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.fline.datasecurity.zj.intercept.AutoResultReturnHandler;


/**
 * web mvc 级别的配置，引入自动封装controller结果的handler
 * 
 * @author zhuzl
 * @date 2020年1月31日
 *
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements InitializingBean {
	@Autowired
	RequestMappingHandlerAdapter requestMappingHandlerAdapter;

	@Override
	public void afterPropertiesSet() throws Exception {
		List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter
				.getReturnValueHandlers();
		List<HandlerMethodReturnValueHandler> list = new ArrayList<>();
		// 自定义returnHandler
		list.add(new AutoResultReturnHandler());
		list.addAll(returnValueHandlers);
		requestMappingHandlerAdapter.setReturnValueHandlers(list);

	}

}
