/**
 * Copyright(C) 2020 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 */
package com.fline.datasecurity.zj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * 
 * @since 2020年3月11日 下午1:40:22
 */
@Configuration
public class MybatisPlusConfig {

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

}
