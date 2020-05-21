package com.fline.datasecurity.zj.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微服务接口的返回结果
 */
@Data
@NoArgsConstructor
public class Result implements Serializable {
	/**
	 * 错误状态码
	 */
	private String code;
	/**
	 * 异常消息
	 */
	private String message;
	/**
	 * 返回的业务数据
	 */
	private Serializable data;
	/**
	 * 请求时间戳
	 */
	private long requestTime;

}
