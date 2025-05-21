package com.karen.pokedex.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karen.pokedex.domain.model.PokemonBasicInfo;
import com.karen.pokedex.domain.model.PokemonDetailFull;
import com.karen.pokedex.domain.service.PokemonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Pokémon", description = "Pokedex")
@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;


    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @Operation(summary = "Pokemon List", description = "Get the list of pokemon with pagination")
    @GetMapping
    public List<PokemonBasicInfo> getPokemonList(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        if (page < 1 || size < 1 || size > 100) {
            throw new IllegalArgumentException("Page and size must be positive integers, size <= 100");
        }
        return pokemonService.getPokemonBasicInfoList(page, size);
    }

    @Operation(summary = "Pokemon Detail", description = "Get the complete detail of the Pokemon")
    @GetMapping("/{name}")
    public PokemonDetailFull getPokemonDetailByName(@PathVariable String name) {
        return pokemonService.getPokemonDetailByName(name);
    }  
}
