package com.azmicro.scms;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

@Configuration
@EnableSwagger2WebFlux
@EnableSwagger2WebMvc
public class SwaggerConfiguration {
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .description("School classroom management system api")
                                .title("SCSM REST API")
                                .build()
                )
                .groupName("REST API V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.azmicro.scms"))
                .paths(PathSelectors.ant(APP_ROOT+"/**"))
                .build()
                ;

    }
}
