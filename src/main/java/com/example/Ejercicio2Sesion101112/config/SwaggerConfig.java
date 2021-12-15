package com.example.Ejercicio2Sesion101112.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * Configuracion Swagger para la generacion de documentacion de APIREST
 *
 * html -> htpp://localhost:8080/swagger-ui/
 * json -> http://localhost:8080/v2/api-docs
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

    }
    private ApiInfo apiDetails(){
        return new ApiInfo("Spring boot Book API REST",
                "Library Api rest docs",
                "1.0",
                "http://unadireccion.com",
                new Contact("Oscar Corral Garcia", "http://midireccion.com","oscar@email.com"),
                "MIT",
                "http://www.google.com",
                Collections.emptyList());
    }
}
