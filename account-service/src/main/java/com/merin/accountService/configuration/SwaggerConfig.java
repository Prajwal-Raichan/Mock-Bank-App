package com.merin.accountService.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI jwtTokenApi() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))  // Add the security requirement for Bearer Auth
                .components(new Components()
                        .addSecuritySchemes("BearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)  // Specify the scheme type as HTTP
                                .scheme("bearer")               // Specify that it's a bearer token
                                .bearerFormat("JWT")))          // Specify that the token format is JWT
                .info(new Info()
                        .title("API Documentation")
                        .version("1.0.0")
                        .description("API documentation with Bearer token authentication"));
    }

}
