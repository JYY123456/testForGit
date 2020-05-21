package com.fline.datasecurity.zj.exception;

import java.io.Serializable;

/**
 * 
 * @author zhuzl
 * @date 2020年3月3日
 *
 */
public class ExcelException extends FlineException {
	private static final long serialVersionUID = 1L;
	public static final ExcelException NORMAL_EXCEL = new ExcelException("11000");
	public static final ExcelException EMPTY_CONTENT = new ExcelException("11001");
	public static final ExcelException EMPTY_MAPPING = new ExcelException("11002");

	public ExcelException(String code, Serializable data) {
		super(code, data);
	}

	public ExcelException(String code, Throwable cause, Serializable data) {
		super(code, cause, data);
	}

	public ExcelException(String code, Throwable cause) {
		super(code, cause);
	}

	public ExcelException(String code) {
		super(code);
	}

}
