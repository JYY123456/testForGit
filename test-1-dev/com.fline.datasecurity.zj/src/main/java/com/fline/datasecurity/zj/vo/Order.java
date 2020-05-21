package com.fline.datasecurity.zj.vo;

import lombok.Getter;

/**
 * 排序规则
 * 
 * @author zhuzl
 * @date 2020年2月24日
 *
 */
@Getter
public class Order {
	/**
	 * 排序列
	 */
	private String column;
	/**
	 * 是否升序，false为降序
	 */
	private boolean asc;

	public Order(String column, boolean asc) {
		this.column = column;
		this.asc = asc;
	}
}
