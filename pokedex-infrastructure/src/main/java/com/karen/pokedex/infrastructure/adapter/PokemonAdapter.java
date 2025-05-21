package com.karen.pokedex.infrastructure.adapter;

import com.karen.pokedex.infrastructure.client.PokemonClient;
import com.karen.pokedex.infrastructure.dto.PokemonDetailResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PokemonAdapter {

    private final PokemonClient pokemonClient;

    public PokemonAdapter(PokemonClient pokemonClient) {
        this.pokemonClient = pokemonClient;
    }

    @Cacheable("pokemonDetails")
    public PokemonDetailResponse getPokemonByName(String name) {
        System.out.println("Calling external API for: " + name);
        return pokemonClient.getPokemonByName(name);
    }
}