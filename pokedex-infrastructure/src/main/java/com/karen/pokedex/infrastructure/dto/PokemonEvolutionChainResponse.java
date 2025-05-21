package com.karen.pokedex.infrastructure.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PokemonEvolutionChainResponse {
     private Chain chain;

    @Data
    public static class Chain {
        private Species species;

        @JsonProperty("evolves_to")
        private List<Chain> evolvesTo;
    }

    @Data
    public static class Species {
        private String name;
    }
}
