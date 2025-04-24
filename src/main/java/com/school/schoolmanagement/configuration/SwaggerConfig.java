package com.school.schoolmanagement.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Liaqat Ali Sanjrani
 *
 *         24-April-2025
 *
 */

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI schoolManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("School Management API")
                        .description("API for managing students, courses, and enrollments")
                        .version("1.0"));
    }
}
