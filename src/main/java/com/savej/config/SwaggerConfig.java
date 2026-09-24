package com.savej.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI ecommerceOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce Application")
                        .description("Sample API for E-Commerce Application")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Mohammad Savej")
                                .email("savejari463@gmail.com")
                                .url("http://mohd.com")));
    }
}