package com.AppWebService.documentation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	public static final Contact DEFAULT_CONTACT = 
			new Contact("Yassine Ouedki", "www.ouedki.com", "Yassine@gmail.pro");
	  public static final ApiInfo DEFAULT_API_INFO = 
			  new ApiInfo("Simple WebApp", "WebApp to demonstrate Restful API","1.0", "urn:tos", 
				DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	private static final Set<String> DEFAUT_PRODUCES_AND_CONSUMES = 
			new HashSet<>(Arrays.asList("application/JSON", "application/xml"));

	  @Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAUT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAUT_PRODUCES_AND_CONSUMES);
	}
}
