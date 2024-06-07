package com.bankaya.api.feign;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PokemonClientConfig {

    @Value("${pokeapi.url}")
    private String url;

    @Bean
    public PokemonClient pokemonClient() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .target(PokemonClient.class, url);
    }
}
