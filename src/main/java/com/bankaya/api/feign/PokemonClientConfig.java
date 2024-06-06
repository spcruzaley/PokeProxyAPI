package com.bankaya.api.feign;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PokemonClientConfig {

    @Bean
    public PokemonClient pokemonClient() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .target(PokemonClient.class, "https://pokeapi.co/api/v2");
    }
}
