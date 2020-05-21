package com.fline.datasecurity.zj.intercept;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhuzl
 *
 */
@Configuration
public class SessionInterceptor implements HandlerInterceptor {
	private static Logger LOG = LogManager.getLogger(SessionInterceptor.class);
	/**
	 * HTTP头部变量中的关键字段
	 */
	public static final String HEADER_TOKEN_FIELD = "token";
	public static final String HEADER_OPENID_FIELD = "openid";
	public static final String HEADER_VALID_MODE = "validmode"; // 验证方式，如果为空，则默认采用JWT
	public static final String HEADER_APPID_FIELD = "appid";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		LOG.info("request请求地址path[{ " + request.getServletPath() + "}];  " + "uri[{" + request.getRequestURI() + "}]");
		return true;
	}

	private boolean isPassableUri(String uri) {
		if (uri.endsWith("/decodeUserInfo") || uri.endsWith("/api/v2/user/base/wxlogin")
				|| uri.endsWith("/api/v1/application_form/form/getFormData") || uri.equalsIgnoreCase("/tabshare/error")
				|| uri.endsWith("/api/v1/application_form/cert/getPdf") || uri.contains("/api/v2/dataservice/")
				|| uri.contains("/api/v2/proxy/")) {
			LOG.debug("received passable uri. uri = " + uri);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
	}

	/**
	 * 基于反射机制，绕过request的parameter保护机制，添加相关参数。
	 * 
	 * @param request
	 * @param key
	 * @param value
	 */
	private void addParameter(HttpServletRequest request, String key, String value) {
		try {
			Class<?> clazz = request.getClass();
			Field requestField = clazz.getDeclaredField("request");
			requestField.setAccessible(true);
			Object innerRequest = requestField.get(request);// 获取到request对象
			// 设置尚未初始化 (否则在获取一些参数的时候，可能会导致不一致)
			Field field = innerRequest.getClass().getDeclaredField("parametersParsed");
			field.setAccessible(true);
			field.setBoolean(innerRequest, false);
			Field coyoteRequestField = innerRequest.getClass().getDeclaredField("coyoteRequest");
			coyoteRequestField.setAccessible(true);
			Object coyoteRequestObject = coyoteRequestField.get(innerRequest);// 获取到coyoteRequest对象
			Field parametersField = coyoteRequestObject.getClass().getDeclaredField("parameters");
			parametersField.setAccessible(true);
			Object parameterObject = parametersField.get(coyoteRequestObject);// 获取到parameter的对象

			Field hashTabArrField = parameterObject.getClass().getDeclaredField("paramHashValues");
			hashTabArrField.setAccessible(true);
			@SuppressWarnings("unchecked")
			Map<String, List<String>> map = (Map<String, List<String>>) hashTabArrField.get(parameterObject);
			List<String> values = new ArrayList<String>();
			values.add(value);
			map.put(key, values);

		} catch (Exception e) {
			LOG.error("add parameter to request failed.", e);
		}
	}
}
