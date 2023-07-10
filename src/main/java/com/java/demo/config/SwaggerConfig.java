package com.java.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	// JWT Authenticate -->
	private static final String BEARER_AUTH = "Bearer";

	private List<SecurityScheme> securitySchemes() {
		return Arrays.asList(new ApiKey(BEARER_AUTH, "Authorization", "header"));
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(Arrays.asList(bearerAuthReference()))
				.build();
	}

	private SecurityReference bearerAuthReference() {
		return new SecurityReference(BEARER_AUTH, new AuthorizationScope[0]);
	}
	//  -->

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.java.demo"))
				.build()
				.apiInfo(metaInfo())
				.securitySchemes(securitySchemes()).securityContexts(Arrays.asList(securityContext()));
	}

    private ApiInfo metaInfo() {

        @SuppressWarnings("deprecation")
		ApiInfo apiInfo = new ApiInfo(
                "Spring Boot Swagger2 Example API",
                null,
                "1.0",
                "Terms of Service",
                null,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/"
        );

        return apiInfo;
    }

}
