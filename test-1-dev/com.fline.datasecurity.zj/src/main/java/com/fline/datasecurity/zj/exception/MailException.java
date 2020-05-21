package com.fline.datasecurity.zj.exception;

import java.io.Serializable;

/**
 * 邮件类的错误
 * 
 * @author zhuzl
 * @date 2020年1月31日
 *
 */
public class MailException extends FlineException {

	private static final long serialVersionUID = 1L;

	public MailException(String code) {
		super(code);
	}

	public MailException(String code, Serializable data) {
		super(code, data);
	}

	public MailException(String code, Throwable cause, Serializable data) {
		super(code, cause, data);
	}

	public MailException(String code, Throwable cause) {
		super(code, cause);
	}

}
