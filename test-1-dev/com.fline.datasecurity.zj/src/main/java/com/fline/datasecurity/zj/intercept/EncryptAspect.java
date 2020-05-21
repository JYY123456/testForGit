/**
 * Copyright(C) 2020 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 */
package com.fline.datasecurity.zj.intercept;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fline.datasecurity.zj.util.EncryptionUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @since 2020年3月8日 下午1:47:43
 */
@Component
@Aspect
@Slf4j
public class EncryptAspect {
	@Autowired
	Environment environment;

	@Pointcut("execution(public String org.springframework.boot.autoconfigure.data.redis.RedisProperties.getPassword())"
			+ " || execution(public String org.springframework.boot.autoconfigure.jdbc.DataSourceProperties.determineUsername())"
			+ " || execution(public String org.springframework.boot.autoconfigure.jdbc.DataSourceProperties.determinePassword())")
	public void redisPoint() {
		// dont do anything
	}

	@Around(value = "redisPoint()")
	public String redisPointafter(ProceedingJoinPoint point) throws Throwable {
		return decrypy(point);
	}

	private String decrypy(ProceedingJoinPoint point) throws Throwable {
		String proceed = (String) point.proceed();
		String defaultAesDecrypt = EncryptionUtil.defaultAesDecrypt(proceed);
		String[] activeProfiles = environment.getActiveProfiles();
		List<Object> asList = Arrays.asList(activeProfiles);
		if (asList != null && asList.contains("dev")) {
			log.debug("before decrypt:{}, after decrypt:{}", proceed, defaultAesDecrypt);
		}
		return defaultAesDecrypt;
	}

}
