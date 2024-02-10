package flavio.projeto.restwithspringbootandjava.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer { // WebMvcConfigurer, que permite a configuração do comportamento do MVC (Model-View-Controller) no contexto da aplicação Spring.
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(true) //Aceita parametros
                .parameterName("mediaType") // Nome do parametro = mediaType
                .ignoreAcceptHeader(true)  // Ignora parâmetros no header
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
    }
}
