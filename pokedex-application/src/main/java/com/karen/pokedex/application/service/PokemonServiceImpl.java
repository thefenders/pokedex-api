package com.karen.pokedex.application.service;

import com.karen.pokedex.domain.model.PokemonBasicInfo;
import com.karen.pokedex.domain.model.PokemonDetailFull;
import com.karen.pokedex.domain.service.PokemonService;
import com.karen.pokedex.infrastructure.adapter.PokemonAdapter;
import com.karen.pokedex.infrastructure.client.PokemonClient;
import com.karen.pokedex.infrastructure.dto.PokemonDetailResponse;
import com.karen.pokedex.infrastructure.dto.PokemonEvolutionChainResponse;
import com.karen.pokedex.infrastructure.dto.PokemonListResponse;
import com.karen.pokedex.infrastructure.dto.PokemonSpeciesResponse;
import com.karen.pokedex.infrastructure.exception.PokemonNotFoundException;
import com.karen.pokedex.infrastructure.util.EvolutionUtil;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokemonClient pokemonClient;
    private final PokemonAdapter pokemonAdapter;

    public PokemonServiceImpl(PokemonClient pokemonClient, PokemonAdapter pokemonAdapter) {
        this.pokemonClient = pokemonClient;
        this.pokemonAdapter = pokemonAdapter;
    }

    @Override
    public List<PokemonBasicInfo> getPokemonBasicInfoList(int page, int size) {
        int offset = (page - 1) * size;
        PokemonListResponse response = pokemonClient.getPokemons(offset, size);

        return response.getResults().stream()
            .map(result -> {
                PokemonDetailResponse detail = pokemonAdapter.getPokemonByName(result.getName());

                return PokemonBasicInfo.builder()
                    .name(detail.getName())
                    .image(detail.getSprites().getFrontDefault())
                    .weight(detail.getWeight())
                    .types(detail.getTypes().stream()
                        .map(type -> type.getType().getName())
                        .collect(Collectors.toList()))
                    .abilities(detail.getAbilities().stream()
                        .map(ability -> ability.getAbility().getName())
                        .collect(Collectors.toList()))
                    .build();
            })
            .collect(Collectors.toList());
    }

    /*
     * 
     */
    @Override
    public PokemonDetailFull getPokemonDetailByName(String name) {
        try{
              // 1. Basic Detail
        PokemonDetailResponse detail = pokemonAdapter.getPokemonByName(name);

        // 2. Specie
        PokemonSpeciesResponse species = pokemonClient.getPokemonSpecies(name);

        // 3. Get the ID from URL of evolution chain
        String evolutionUrl = species.getEvolutionChain().getUrl();
        int evolutionId = EvolutionUtil.extractEvolutionId(evolutionUrl);

        // 4. Get evolution chain
        PokemonEvolutionChainResponse evolutionChain = pokemonClient.getEvolutionChain(evolutionId);
        List<String> evolutions = EvolutionUtil.extractEvolutions(evolutionChain.getChain());

        return PokemonDetailFull.builder()
        .name(detail.getName())
        .image(detail.getSprites().getFrontDefault())
        .weight(detail.getWeight())
        .types(detail.getTypes().stream()
                .map(type -> type.getType().getName())
                .collect(Collectors.toList()))
        .abilities(detail.getAbilities().stream()
                .map(ability -> ability.getAbility().getName())
                .collect(Collectors.toList()))
        .description(
            Arrays.stream(species.getFlavorTextEntries())
                  .filter(entry -> "en".equals(entry.getLanguage().getName()))
                  .findFirst()
                  .map(entry -> entry.getFlavorText().replace("\n", " ").replace("\f", " "))
                  .orElse("No description available.")
        )
        .evolutions(evolutions)
        .build();
        } catch (Exception ex) {
             throw new PokemonNotFoundException(name);
        }
    
    }
}