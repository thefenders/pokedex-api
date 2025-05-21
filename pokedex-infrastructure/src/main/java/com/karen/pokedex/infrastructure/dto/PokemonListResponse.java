package com.karen.pokedex.infrastructure.dto;

import lombok.Data;
import java.util.List;

@Data
public class PokemonListResponse {
    private int count;
    private String next;
    private String previous;
    private List<PokemonResult> results;

    @Data
    public static class PokemonResult {
        private String name;
        private String url;
    }
}