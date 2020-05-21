package com.fline.datasecurity.zj.vo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页查询条件
 * 
 * @author zhuzl
 * @date 2020年2月24日
 *
 */
@Setter
@Getter
public class PageCondition {
	/**
	 * 排序规则
	 */
	private Order order;
	/**
	 * 每页返回的数量
	 */
	private int pageSize = 10;
	/**
	 * 返回哪一页
	 */
	private int pageIndex = 1;

	/**
	 * 转成Map形式的查询条件
	 * 
	 * @return
	 */
	public Map<String, Object> toQueryCondition() {
		Map<String, Object> param = new HashMap<String, Object>();
		if (this.order != null && !StringUtils.isEmpty(this.order.getColumn())) {
			param.put("_ORDER_BY_", " order by " + this.order.getColumn() + " " + (order.isAsc() ? "asc" : "desc"));
		}
		int pageSize = this.pageSize <= 0 ? 10 : this.pageSize;
		int pageIndex = this.pageIndex <= 0 ? 1 : this.pageIndex;
		param.put("_LIMIT_START_", (pageIndex - 1) * pageSize);
		param.put("_LIMIT_NUM_", pageSize);
		return param;
	}
}
