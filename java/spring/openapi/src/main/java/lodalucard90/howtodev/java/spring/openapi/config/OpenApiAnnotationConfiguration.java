package lodalucard90.howtodev.java.spring.openapi.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                version = "1.0",
                title = "Basic Example",
                description = "Basic specification annotation example",
                termsOfService = "http://example.com/terms/",
                contact = @Contact(
                        name = "API Support",
                        url = "http://www.example.com/support",
                        email = "support@example.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        servers = {
                @Server(
                        url = "http://example.com/api",
                        description = "My example server"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "BasicAuth"
                ),
                @SecurityRequirement(
                        name = "JwtAuthToken"
                )
        },
        externalDocs = @ExternalDocumentation(
                url = "http://example.com/external",
                description = "External Documentation"
        )
)
@SecuritySchemes({
        @SecurityScheme(
                name = "BasicAuth",
                type = SecuritySchemeType.HTTP,
                scheme = "basic"
        ),
        @SecurityScheme(
                name = "JwtAuthToken",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT"
        )
})
@Configuration
public class OpenApiAnnotationConfiguration {
}
