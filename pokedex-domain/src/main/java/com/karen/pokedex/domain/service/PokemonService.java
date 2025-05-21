package com.karen.pokedex.domain.service;

import java.util.List;
import com.karen.pokedex.domain.model.PokemonBasicInfo;
import com.karen.pokedex.domain.model.PokemonDetailFull;

public interface PokemonService {
    List<PokemonBasicInfo> getPokemonBasicInfoList(int page, int size);
    PokemonDetailFull getPokemonDetailByName(String name);
}