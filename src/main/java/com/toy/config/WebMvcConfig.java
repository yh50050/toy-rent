package com.toy.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/index").setViewName("index");

		super.addViewControllers(registry);
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(false);
		super.configurePathMatch(configurer);
	}

	/**
	 * 过滤器
	 * 
	 * @return
	 */
	// @Bean
	// public FilterRegistrationBean siteMeshFilter() {
	// FilterRegistrationBean fitler = new FilterRegistrationBean();
	// SiteMeshConfig siteMeshFilter = new SiteMeshConfig();
	// fitler.setFilter(siteMeshFilter);
	// return fitler;
	// }

	@Bean
	public ObjectMapper ObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		// 设置null值不参与序列化(字段不被显示)
		// objectMapper.setSerializationInclusion(Include.NON_NULL);
		// null替换为""
		objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2)
					throws IOException, JsonProcessingException {
				arg1.writeString("");
			}
		});
		// 禁用空对象转换json校验
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 驼峰命名法转换为小写加下划线
		// objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		return objectMapper;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/",
				"/employee/login", "/admin/login", "/services/**");
		super.addInterceptors(registry);
	}
}
