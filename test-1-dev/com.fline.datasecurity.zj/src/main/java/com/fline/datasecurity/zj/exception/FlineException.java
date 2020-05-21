package com.fline.datasecurity.zj.exception;

import java.io.Serializable;

public class FlineException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private Serializable data;

	private Object[] objects;

	public FlineException(String code) {
		super(code);
	}

	public FlineException(String code, Serializable data) {
		super(code);
		this.data = data;
	}

	public FlineException(String code, Throwable cause) {
		super(code, cause);
	}

	public FlineException(String code, Throwable cause, Serializable data) {
		super(code, cause);
		this.data = data;
	}

	public Serializable getData() {
		return data;
	}

	public Object[] getObjects() {
		return objects;
	}

	public void setObjects(Object... objects) {
		this.objects = objects;
	}

}
