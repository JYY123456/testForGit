package com.fline.datasecurity.core.zj;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication(scanBasePackages = { "com.fline.datasecurity" }, exclude = {
		MongoAutoConfiguration.class, MongoDataAutoConfiguration.class, MongoReactiveAutoConfiguration.class,
		MongoReactiveDataAutoConfiguration.class, QuartzAutoConfiguration.class, SecurityAutoConfiguration.class })
@MapperScan(basePackages = { "com.fline.datasecurity.zj.dao.mapper", "com.fline.datasecurity.core.zj.dao.mapper" })
@ComponentScan(basePackages = { "com.fline.datasecurity"})
@ImportResource(locations = { "classpath:system/restTemplate.xml", "classpath:system/transaction.xml" })
@EnableCaching
@EnableScheduling
@ServletComponentScan
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class DatasecurityStarter extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(DatasecurityStarter.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DatasecurityStarter.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DatasecurityStarter.class, args);
		log.info("服务启动成功!");
	}

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(5000);
		threadPoolTaskExecutor.setMaxPoolSize(5000);
		threadPoolTaskExecutor.setQueueCapacity(5000);
		threadPoolTaskExecutor.setThreadNamePrefix("task-pool-");
		threadPoolTaskExecutor.setKeepAliveSeconds(600);
		return threadPoolTaskExecutor;
	}
}
