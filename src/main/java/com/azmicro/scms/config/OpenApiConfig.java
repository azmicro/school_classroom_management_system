package com.azmicro.scms.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SCMS REST API")
                        .version("1.0.0")
                        .description("School Classroom Management System"))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentation")
                        .url("https://example.com/docs"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        String[] packagesToScan = {"com.azmicro.scms"};
        return GroupedOpenApi.builder()
                .group("public-api")
                .packagesToScan(packagesToScan)
                .pathsToMatch(APP_ROOT+"/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        String[] packagesToScan = {"com.azmicro.scms"};
        return GroupedOpenApi.builder()
                .group("admin-api")
                .packagesToScan(packagesToScan)
                .group("REST API V1")
                .pathsToMatch(APP_ROOT+"/**")
                .build();
    }

}
