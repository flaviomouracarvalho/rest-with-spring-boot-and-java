package flavio.projeto.restwithspringbootandjava.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("RESTFul API with java 21 and Spring Boot 3")
                        .version("v1")
                        .description("Some description about your API.")
                        .termsOfService("https://github.com/flaviomouracarvalho/rest-with-spring-boot-and-java")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/flaviomouracarvalho/rest-with-spring-boot-and-java")));
    }
}
