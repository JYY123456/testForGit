package com.fline.datasecurity.zj.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 封装返回结果的注解类
 * 
 * @author zhuzl
 * @date 2020年2月11日
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.TYPE, ElementType.METHOD })
public @interface AutoResult {

}
