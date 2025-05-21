package com.karen.pokedex.domain.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PokemonBasicInfo {
    private String name;
    private String image;
    private int weight;
    private List<String> types;
    private List<String> abilities;
}
