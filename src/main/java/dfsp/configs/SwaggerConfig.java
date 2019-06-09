package dfsp.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static dfsp.configs.Naming.BASE_PACKAGE_CONTROLLERS;

/*
*
* Klasa konfiguracyjna bibliotekę Swagger - automatyczną dokumentację REST API.
* */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket config() {
        return new Docket(DocumentationType.SWAGGER_12)
                .select()
                .apis(RequestHandlerSelectors.basePackage("${package.controllers}"))
                .build();
    }
}
