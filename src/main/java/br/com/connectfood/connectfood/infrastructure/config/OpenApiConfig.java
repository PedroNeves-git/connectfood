package br.com.connectfood.connectfood.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        System.out.println("Swagger OpenAPI Configurado!");
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info()
                        .title("Connectfood")
                        .description("API para gerenciar restaurantes e usuarios")
                        .version("2.0.0")
                        .contact(new Contact()
                                .name("Pedro Henrique Neves dos Santos")
                                .email("sgtpedrocos@gmail.com")
                                .url("https://github.com/PedroNeves-git")
                        )
                );
    }
}