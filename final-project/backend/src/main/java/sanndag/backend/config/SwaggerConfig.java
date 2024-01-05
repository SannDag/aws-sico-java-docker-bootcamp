package sanndag.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Persistence API REST",
                description = "A concise overview of the project focusing on its role as a RESTful Persistence API.",
                version = "1.0",
                license = @License(
                        name = "MIT License",
                        url= "https://choosealicense.com/licenses/mit/"),
                termsOfService = "Terms of service",
                contact = @Contact(
                        name = "sanndag",
                        email = "sanndag.dev@gmail.com",
                        url = "https://www.github.com/sanndag/"
                )
        ),
        servers = {
                @Server(
                        description = "Local environment API server",
                        url = "http://localhost:8080"
                )
        }
)
public class SwaggerConfig {}
