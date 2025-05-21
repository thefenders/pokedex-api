package com.karen.pokedex.infrastructure.client;

import com.karen.pokedex.infrastructure.dto.PokemonDetailResponse;
import com.karen.pokedex.infrastructure.dto.PokemonEvolutionChainResponse;
import com.karen.pokedex.infrastructure.dto.PokemonListResponse;
import com.karen.pokedex.infrastructure.dto.PokemonSpeciesResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pokemonClient", url = "https://pokeapi.co/api/v2")
public interface PokemonClient {

    @GetMapping("/pokemon")
    PokemonListResponse getPokemons(@RequestParam("offset") int offset, @RequestParam("limit") int limit);

    @GetMapping("/pokemon/{name}")
    PokemonDetailResponse getPokemonByName(@PathVariable("name") String name);

    @GetMapping("/pokemon-species/{name}")
    PokemonSpeciesResponse getPokemonSpecies(@PathVariable("name") String name);

    @GetMapping("/evolution-chain/{id}")
    PokemonEvolutionChainResponse getEvolutionChain(@PathVariable("id") int id);
}
