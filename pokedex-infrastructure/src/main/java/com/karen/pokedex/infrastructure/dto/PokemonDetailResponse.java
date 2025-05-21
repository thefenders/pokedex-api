package com.karen.pokedex.infrastructure.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Data;

@Data
public class PokemonDetailResponse {
    private String name;
    private int weight;
    private Sprites sprites;
    private List<TypeWrapper> types;
    private List<AbilityWrapper> abilities;

    @Data
    public static class Sprites {
        @JsonProperty("front_default")
        private String frontDefault;
    }
    
    @Data
    public static class TypeWrapper {
        private Type type;

        @Data
        public static class Type {
            private String name;
        }
    }

     @Data
    public static class AbilityWrapper {
        private Ability ability;

        @Data
        public static class Ability {
            private String name;
        }
    }
}
