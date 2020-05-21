package com.fline.datasecurity.core.zj.config;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.util.ClassUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fline.datasecurity.zj.exception.FlineException;

/**
 * Redis配置类，产生jedisCluster类
 * 
 * @date 2020年2月11日
 *
 */
@Configuration
public class RedisConfig {

	private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);
	protected static final Object NULL_PARAM_KEY = "";

	/**
	 * key前缀，用于区分不同项目的缓存，建议每个项目单独设置
	 */
	@Value("${spring.cache.redis.key-prefix:'fline'}")
	private String keyPrefix;

	@Bean
	public CacheManager redisCacheManager(JedisConnectionFactory jedisConnectionFactory) {
		RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(jedisConnectionFactory);
		// 默认缓存1小时
		RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofHours(1));
		Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
		// cachename为testCache时缓存10分钟，可在cacheConfigurations中继续设置其他缓存配置
		cacheConfigurations.put("testCache",
				RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)));
		builder.cacheDefaults(defaultCacheConfiguration).withInitialCacheConfigurations(cacheConfigurations);
		return builder.build();
	}

	/**
	 * 自动生成key,需要指定后生效 例如@CacheConfig(keyGenerator = "myKeyGenerator")
	 * 
	 * @since 2020年3月10日 下午2:35:18
	 * @return
	 */
	@Bean
	public KeyGenerator myKeyGenerator() {
		return new KeyGenerator() {

			@Autowired
			ObjectMapper objectMapper;

			char sp = ':';

			@Override
			public Object generate(Object target, Method method, Object... params) {
				log.debug("{}", method);

				StringBuilder strBuilder = new StringBuilder(30);
				strBuilder.append(keyPrefix);
				strBuilder.append(sp);
				// 类名
				strBuilder.append(target.getClass().getSimpleName());
				strBuilder.append(sp);
				// 方法名
				strBuilder.append(method.getName());
				strBuilder.append(sp);
				if (params.length > 0) {
					// 参数值
					for (Object object : params) {
						if (object == null) {
							object = "null";
						}
						if (isSimpleValueType(object.getClass())) {
							strBuilder.append(object.toString());
						} else {
							try {
								strBuilder.append(objectMapper.writeValueAsString(object));
							} catch (JsonProcessingException e) {
								throw new FlineException("-1", e);
							}
						}
					}
				}
				String string = strBuilder.toString();
				log.debug("{}", string);
				return string;
			}

			private boolean isSimpleValueType(Class<?> clazz) {
				return (ClassUtils.isPrimitiveOrWrapper(clazz) || clazz.isEnum()
						|| CharSequence.class.isAssignableFrom(clazz) || Number.class.isAssignableFrom(clazz)
						|| Date.class.isAssignableFrom(clazz) || URI.class == clazz || URL.class == clazz
						|| Locale.class == clazz || Class.class == clazz);
			}
		};
	}

}