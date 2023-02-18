package com.radzik.michal.shop.user.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI (){
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("BasicToken", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic"))
                        .addSecuritySchemes("BearerToken", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title("User microservise API")
                        .description("User microservice description")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Michał Radzik")
                                .url("")
                                .email("michal.radzik@wp.pl")));

    }


}
