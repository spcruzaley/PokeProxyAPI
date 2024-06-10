package com.bankaya.api.feign;

import com.bankaya.schema.generated.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PokemonClientService {

    @Autowired
    private PokemonClient pokemonClient;

    @Cacheable("apiCache")
    public Root getPokemonByName(String pokemon) {
        return pokemonClient.getPokemonByName(pokemon);
    }
}
