package com.fline.datasecurity.zj.intercept;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSONObject;

/**
 * 服务拦截器，根据Http Header中的cityCode信息选择真实的服务地址。
 * 
 * @author zhuzl
 *
 */
@Configuration
public class ServiceInterceptor implements HandlerInterceptor {
	private static Logger LOG = LogManager.getLogger(ServiceInterceptor.class);
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUri = request.getRequestURI();
		// 只拦截满足条件的请求
		Short type = INTERFACE_RESULT_TYPE_MAP.get(requestUri);
		if (type == null || (type != 2 && type != 1)) {
			// 不在拦截范围内
			return true;
		}
		String areaCode = request.getHeader("areaCode");
		if (StringUtils.isEmpty(areaCode)) {
			// 未带cityCode，则无指向性服务，故而由本服务处理。
			return true;
		} else {
//			try {
//				AreaServer areaServer = areaServerServivce.findByAreacode(areaCode);
//				if (areaServer != null) {
//					// 转发请求
//					exchangeRequest(areaServer.getServer(), request, response);
//					return false;
//				} else {
//					// 后续为了防止少配的情况
//					String cityCode = areaCode.substring(0, 4) + "00";
//					areaServer = areaServerServivce.findByAreacode(cityCode);
//					if (areaServer != null) {
//						// 转发请求
//						exchangeRequest(areaServer.getServer(), request, response);
//						return false;
//					} else {
//						String provinceCode = areaCode.substring(0, 2) + "0000";
//						areaServer = areaServerServivce.findByAreacode(provinceCode);
//						if (areaServer != null) {
//							// 转发请求
//							exchangeRequest(areaServer.getServer(), request, response);
//							return false;
//						} else {
//							// 本机执行
//							return true;
//						}
//					}
//				}
//			} catch (Exception e) {
//				LOG.error("exhange request failed.", e);
//				return true;
//			}
			return true;
		}
	}

	/**
	 * 将request请求转发到destServerUri上，并接收返回JSON格式的数据。
	 * 
	 * @param destServerUri
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void exchangeRequest(String destServerUri, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String requestUri = request.getRequestURI();
		HttpMethod method = HttpMethod.valueOf(request.getMethod());
		String inputData = IOUtils.toString(request.getInputStream(), "utf-8");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(inputData, headers);
		// 根据响应结果的类型定义不同的ResponseEntity
		Short type = INTERFACE_RESULT_TYPE_MAP.get(requestUri);
		if (type == JSON_RESULT_REQUEST) {
			ResponseEntity<JSONObject> rss = restTemplate.exchange(destServerUri + requestUri, method, entity,
					JSONObject.class);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(rss.getBody().toString());
		} else if (type == STRING_RESULT_REQUEST) {
			ResponseEntity<String> rss = restTemplate.exchange(destServerUri + requestUri, method, entity,
					String.class);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(rss.getBody());
		} else {
			throw new IOException("Unsupport result type = " + type);
		}
	}

	/**
	 * 常量， 表征接口返回的数据格式
	 */
	private static final short STRING_RESULT_REQUEST = 1;
	private static final short JSON_RESULT_REQUEST = 2;
	private static Map<String, Short> INTERFACE_RESULT_TYPE_MAP = new HashMap<String, Short>();
	static {
		// 暂无拦截项
//		INTERFACE_RESULT_TYPE_MAP.put("/tabshare/history/item/findHotspotItem", JSON_RESULT_REQUEST);
	}
}
