package com.karen.pokedex.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PokemonControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnListOfPokemons() throws Exception {
        mockMvc.perform(get("/pokemon?page=1&size=1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    void shouldReturnDetailOfPokemon() throws Exception {
        mockMvc.perform(get("/pokemon/pikachu"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("pikachu"))
            .andExpect(jsonPath("$.description").exists());
    }

    @Test
    void shouldReturnNotFoundForInvalidPokemon() throws Exception {
        mockMvc.perform(get("/pokemon/invalid"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.error").value("Not Found"));
    }
}
