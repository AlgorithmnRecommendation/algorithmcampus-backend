package com.algorithmcampus.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * swagger config
 *
 * @author : 권지영
 * @filename : Swaggerconfig
 * @since : 2026. 4. 16. 목요일
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AlgorithmCampus API")
                        .description("AlgorithmCampus API 문서")
                        .version("v1.0.0"));
    }
}
