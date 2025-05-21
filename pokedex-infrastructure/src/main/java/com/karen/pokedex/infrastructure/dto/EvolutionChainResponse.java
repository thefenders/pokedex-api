package com.karen.pokedex.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EvolutionChainResponse {
     private int id;

    @JsonProperty("chain")
    private PokemonEvolutionChainResponse chain;
}
