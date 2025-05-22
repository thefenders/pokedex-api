package com.karen.pokedex.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI pokedexOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Pokedex API")
                .version("1.0.0")
                .description("API to fetch paginated Pokemons with basic info"));
    }
}
