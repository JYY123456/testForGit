package com.fline.datasecurity.zj.util;

import javax.servlet.http.HttpServletRequest;

/**
 * HTTP 请求解析器
 * 
 * @author zhuzl
 * @date 2019.12.29
 *
 */
public class HttpRequestParser {
	/**
	 * 获取请求源的IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestSourceIpAddress(HttpServletRequest request) {
		String ipAddr = request.getHeader("x-forwarded-for");
		if (ipAddr == null || ipAddr.length() <= 0 || "unknown".equalsIgnoreCase(ipAddr)) {
			ipAddr = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddr == null || ipAddr.length() <= 0 || "unknown".equalsIgnoreCase(ipAddr)) {
			ipAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddr == null || ipAddr.length() <= 0 || "unknown".equalsIgnoreCase(ipAddr)) {
			ipAddr = request.getRemoteAddr();
			// 本地地址，统一返回127.0.0.1
			if (ipAddr.equals("127.0.0.1") || ipAddr.equals("0:0:0:0:0:0:0:1")) {
				ipAddr = "127.0.0.1";
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddr != null && ipAddr.length() > 15) {
			if (ipAddr.indexOf(',') > 0) {
				ipAddr = ipAddr.substring(0, ipAddr.indexOf(','));
			}
		}
		return ipAddr;
	}

}
