    package com.example.clienttracking.config;

    import io.swagger.v3.oas.models.OpenAPI;
    import io.swagger.v3.oas.models.info.Info;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;

    @Configuration
    public class SwaggerConfig {

        @Bean
        public OpenAPI apiInfo() {
            
            return new OpenAPI()

                    .info(new Info()
                            .title("Client Tracking System API")
                            .description("API documentation for Client-Resource Time Tracking System")
                            .version("1.0.0"));
        }
    }