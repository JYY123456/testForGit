package com.fline.datasecurity.zj.intercept;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fline.datasecurity.zj.exception.FlineException;
import com.fline.datasecurity.zj.vo.Result;


/**
 * controller 异常处理
 * 
 * @author zhuzl
 * @date 2020年1月31日
 *
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

	private static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

	// 特殊错误, 未定义的错误
	private static final String NORMAL_EXCEPTION_CODE = "-1";

	@Autowired
	MessageSource messageSource;
	@Autowired
	HttpServletRequest request;

	@ExceptionHandler(FlineException.class)
	public Result excelException(FlineException e) {
		return getErrorResult(e);
	}

	@ExceptionHandler(Exception.class)
	public Result normalException(Exception e) {
		long currentTimeMillis = System.currentTimeMillis();
		log.error("{}", currentTimeMillis, e);
		String message = messageSource.getMessage(NORMAL_EXCEPTION_CODE, null, request.getLocale());
		Result r = new Result();
		r.setRequestTime(currentTimeMillis);
		r.setCode(NORMAL_EXCEPTION_CODE);
		r.setMessage(message);
		return r;
	}

	/**
	 * 统一方式生成错误类的返回值
	 * 
	 * @param typeCode
	 * @param e
	 * @return
	 */
	private Result getErrorResult(FlineException e) {
		long currentTimeMillis = System.currentTimeMillis();
		log.warn("{}", currentTimeMillis, e);
		Result r = new Result();
		String message = null;
		String code = e.getMessage();
		Object[] objects = e.getObjects();
		try {
			message = messageSource.getMessage(code, objects, request.getLocale());
		} catch (NoSuchMessageException e1) {
			code = "00";
			message = messageSource.getMessage(code, objects, request.getLocale());
		}
		r.setCode(code);
		r.setMessage(message);
		r.setRequestTime(currentTimeMillis);
		r.setData(e.getData());
		return r;
	}
}
