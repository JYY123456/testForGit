package com.fline.datasecurity.zj.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fline.datasecurity.zj.util.HttpRequestParser;


/**
 * IP 拦截器，限制访问知识图谱服务
 * 
 * @author zhuzl
 *
 */
public class IpInterceptor implements HandlerInterceptor {
	private static Logger LOG = LogManager.getLogger(IpInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ipAddr = HttpRequestParser.getRequestSourceIpAddress(request);
		LOG.debug("receive request from " + ipAddr + ", requestUri: '" + request.getRequestURI() + "'");
		return true;
	}
}
