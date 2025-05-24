package com.bluefox.Pizzeria.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                    .title("Pizzeria API")
                    .version("v1")
                    .description("API documentation for the Pizzeria application")
                    .license(new License()
                            .name("MIT License")
                            .url("https://github.com/FelipeFerraz4/pizzeria/blob/main/LICENSE")
                    )
            );
    }
}
