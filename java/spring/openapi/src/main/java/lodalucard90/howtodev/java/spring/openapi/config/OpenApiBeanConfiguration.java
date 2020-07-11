package lodalucard90.howtodev.java.spring.openapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiBeanConfiguration {
    // annotation has the priority, comment it to use the bean configuration
    @Bean
    public OpenAPI getOpenApiConfiguration() {
        Contact contact = new Contact()
                        .name("API Support")
                        .url("http://www.example.com/support")
                        .email("support@example.com");

        License license = new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0.html");

        Info info = new Info()
                .version ("1.0")
                .title("Basic Example")
                .description("Basic specification bean example")
                .termsOfService("http://example.com/terms/")
                .contact(contact)
                .license(license);

        Server server = new Server()
                .url("http://example.com/api")
                .description("My example server");

        SecurityScheme basicAuthSecurityScheme = new SecurityScheme()
                .name("BasicAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic");

        SecurityScheme jwtAuthTokenSecurityScheme = new SecurityScheme()
                .name("JwtAuthToken")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        SecurityRequirement basicAuth = new SecurityRequirement().addList("BasicAuth");
        SecurityRequirement jwtAuthToken = new SecurityRequirement().addList("JwtAuthToken");

        ExternalDocumentation documentation = new ExternalDocumentation()
                .url("http://example.com/external")
                .description("External Documentation");

        return new OpenAPI()
                .info(info)
                .components(new Components())
                .servers(List.of(server))
                .security(List.of(basicAuth, jwtAuthToken))
                .schemaRequirement("BasicAuth", basicAuthSecurityScheme)
                .schemaRequirement("JwtAuthToken", jwtAuthTokenSecurityScheme)
                .externalDocs(documentation);
    }
}
