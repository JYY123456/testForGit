package com.fline.datasecurity.zj.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户会话的上下文信息
 * 
 * @author zhuzl
 * @date 2020年2月26日
 *
 */
@Setter
@Getter
public class UserSessionContext {

	private static ThreadLocal<UserSessionContext> context = new ThreadLocal<UserSessionContext>();
	/** session信息 */
	private UserSession userSession;
	/** 用户信息 */
	private UserContext userContext;

	public static UserSessionContext getUserSessionContext() {
		return context.get();
	}
}
