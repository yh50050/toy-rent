package com.toy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author yanhu
 * @time 2017-11-7
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.toy.controller")).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("阎虎", "", "yanhu32@163.com");
		return new ApiInfo("Toy-Rent后台API接口", // 大标题 title
				"Toy-Rent后台API接口", // 小标题
				"0.0.1", // 版本
				"", // termsOfServiceUrl
				contact, // 作者
				"", // 链接显示文字
				""// 网站链接
		);
	}

}
