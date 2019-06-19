package com.byteparity.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring Boot application startup. When refactoring or moving classes around, remember
 * to keep this at the top of all packages, as Spring expects that.
 * Otherwise, all hell will break loose, and nothing will work.
 *
 * Some documentation:
 * - https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#production-ready-endpoints
 * - http://www.baeldung.com/spring-boot-actuators
 * - Swagger: http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
 * - Swagger: http://localhost:8080/swagger-ui.html
 *
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.byteparity.employee.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("ByteParity Employees", "http://first.last", "first.last@firstlast.ww");
        return new ApiInfoBuilder()
                .title("ByteParity Employees")
                .description("Rest API")
                // .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
                .contact(contact)
                // .license("Apache License Version 2.0")
                // .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0")
                .build();
    }
}
