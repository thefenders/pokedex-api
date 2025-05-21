package com.karen.pokedex.infrastructure.util;

import java.util.ArrayList;
import java.util.List;

import com.karen.pokedex.infrastructure.dto.PokemonEvolutionChainResponse;

public class EvolutionUtil {

    public static int extractEvolutionId(String url) {
        String[] parts = url.split("/");
        return Integer.parseInt(parts[parts.length - 1]);
    }
    
    public static List<String> extractEvolutions(PokemonEvolutionChainResponse.Chain chain) {
        List<String> result = new ArrayList<>();
        extract(chain, result);
        return result;
    }

    public static void extract(PokemonEvolutionChainResponse.Chain chain, List<String> result) {
        result.add(chain.getSpecies().getName());
        for(PokemonEvolutionChainResponse.Chain next : chain.getEvolvesTo()) {
            extract(next, result);
        }
    }
}
