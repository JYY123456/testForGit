package com.fline.datasecurity.zj.intercept;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;
import com.fline.datasecurity.zj.annotation.AutoResult;
import com.fline.datasecurity.zj.vo.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * 对正常返回结果的封装
 * 
 * @author zhuzl
 * @date 2020年1月31日
 *
 */
@Slf4j
public class AutoResultReturnHandler implements HandlerMethodReturnValueHandler {

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		AutoResult autoResultAnnation = returnType.getAnnotatedElement().getAnnotation(AutoResult.class);
		return autoResultAnnation != null;
	}

	@Override
	public void handleReturnValue(Object returnData, MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		Serializable returnValue = (Serializable) returnData;
		mavContainer.setRequestHandled(true);
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		response.setContentType("text/json;charset=UTF-8");

		Result r = new Result();
		// 执行正确的代码
		r.setCode("600");
		r.setData(returnValue);

		try (PrintWriter writer = response.getWriter();) {
			writer.print(JSON.toJSONString(r));
			writer.flush();
		} catch (IOException e) {
			log.error("set auto resultl failed.", e);
		}
	}

}
