package com.karen.pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {
    "com.karen.pokedex.controller",
    "com.karen.pokedex.application",
    "com.karen.pokedex.infrastructure",
    "com.karen.pokedex.domain"
})
@EnableFeignClients(basePackages = "com.karen.pokedex.infrastructure.client")
@EnableCaching
public class PokedexApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokedexApiApplication.class, args);
    }
}