package com.karen.pokedex.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.karen.pokedex.domain.model.PokemonBasicInfo;
import com.karen.pokedex.infrastructure.adapter.PokemonAdapter;
import com.karen.pokedex.infrastructure.client.PokemonClient;
import com.karen.pokedex.infrastructure.dto.PokemonDetailResponse;
import com.karen.pokedex.infrastructure.dto.PokemonListResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceImplTest {
    @Mock
    private PokemonClient pokemonClient;

    @Mock
    private PokemonAdapter pokemonAdapter;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Test
    void shouldReturnBasicInfoList() {
         // Arrange
        PokemonListResponse response = new PokemonListResponse();
        PokemonListResponse.PokemonResult result = new PokemonListResponse.PokemonResult();
        result.setName("bulbasaur");
        response.setResults(List.of(result));

        PokemonDetailResponse detail = new PokemonDetailResponse();
        detail.setName("bulbasaur");
        detail.setWeight(69);

        // Sprites
        PokemonDetailResponse.Sprites sprites = new PokemonDetailResponse.Sprites();
        sprites.setFrontDefault("https://example.com/bulbasaur.png");
        detail.setSprites(sprites);

        // Types
        PokemonDetailResponse.TypeWrapper.Type type = new PokemonDetailResponse.TypeWrapper.Type();
        type.setName("grass");

        PokemonDetailResponse.TypeWrapper typeWrapper = new PokemonDetailResponse.TypeWrapper();
        typeWrapper.setType(type);
        detail.setTypes(List.of(typeWrapper));

        // Abilities
        PokemonDetailResponse.AbilityWrapper.Ability ability = new PokemonDetailResponse.AbilityWrapper.Ability();
        ability.setName("overgrow");

        PokemonDetailResponse.AbilityWrapper abilityWrapper = new PokemonDetailResponse.AbilityWrapper();
        abilityWrapper.setAbility(ability);
        detail.setAbilities(List.of(abilityWrapper));

        when(pokemonClient.getPokemons(0, 1)).thenReturn(response);
        when(pokemonAdapter.getPokemonByName("bulbasaur")).thenReturn(detail);

        // Act
        List<PokemonBasicInfo> pokemons = pokemonService.getPokemonBasicInfoList(1, 1);

        // Assert
        assertEquals(1, pokemons.size());
        PokemonBasicInfo info = pokemons.get(0);
        assertEquals("bulbasaur", info.getName());
        assertEquals("https://example.com/bulbasaur.png", info.getImage());
        assertEquals(69, info.getWeight());
        assertEquals(List.of("grass"), info.getTypes());
        assertEquals(List.of("overgrow"), info.getAbilities());
    }
}
