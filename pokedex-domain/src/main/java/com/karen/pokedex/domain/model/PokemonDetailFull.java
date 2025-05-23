package com.karen.pokedex.domain.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PokemonDetailFull {
    private String name;
    private String image;
    private int weight;
    private List<String> types;
    private List<String> abilities;
    private String description;
    private List<String> evolutions;

    
}
