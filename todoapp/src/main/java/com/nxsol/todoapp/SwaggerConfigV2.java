package com.nxsol.todoapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfigV2 {

	@ConditionalOnMissingBean
	@Bean
	public Docket docket() {
		List<ResponseMessage> list = new ArrayList<>();
		list.add(new ResponseMessageBuilder().code(500).message("Internal Server Error").build());
		list.add(new ResponseMessageBuilder().code(403).message("Forbidden").build());

		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.nxsol"))
				.build()
				.apiInfo(apiInfo());
	}

	@ConditionalOnMissingBean
	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API Documents")
				.description("Documents with Swagger 2")
				.termsOfServiceUrl("http://www.nxsol.com/")
				.contact(new Contact("Minesh", "JAVA", "minesh.b.java@gmail.com"))
				.license("nxsol.com")
				.licenseUrl("http://www.nxsol.com/")
				.version("1.0")
				.build();
	}
	
	@Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
            .displayRequestDuration(true)
            .validatorUrl("")
            .build();
    }
}
