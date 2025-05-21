package com.karen.pokedex.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PokemonSpeciesResponse {
     @JsonProperty("flavor_text_entries")
    private FlavorTextEntry[] flavorTextEntries;

    @JsonProperty("evolution_chain")
    private EvolutionChainUrl evolutionChain;

    @Data
    public static class FlavorTextEntry {
        @JsonProperty("flavor_text")
        private String flavorText;

        private Language language;
    }

    @Data
    public static class Language {
        private String name;
    }

    @Data
    public static class EvolutionChainUrl {
        private String url;
    }
}
