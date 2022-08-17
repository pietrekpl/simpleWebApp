package com.mastery.java.task.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Employee Manager API",
        description = "Application to manage employees. Provides actions: adding, getting, updating and deleting employees  ",
        termsOfService = "http://example.com/terms/",
        contact = @Contact(name = "p.ludynia@godeltech.com", email = "p.ludynia@godeltech.com"),
        license = @License(name = "License", url = "https://www.apache.org/licenses/LICENSE-2.0.html"),
        version = "1.0"))
public class OpenAPiConfiguration {

}
