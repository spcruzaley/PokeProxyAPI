package com.bankaya.api;

import com.bankaya.api.feign.PokemonClient;
import com.bankaya.schema.generated.Root;
import org.springframework.beans.factory.annotation.Autowired;

public class TestClass {

    @Autowired
    static PokemonClient pokemonClient;

    public static void main(String[] args) {
        //PokemonClient client = PokemonClientConfig.createClient();

        String pokemonName = "pikachu"; // Ejemplo de parámetro proporcionado en el request
        Root response = pokemonClient.getPokemonByName(pokemonName);

        // Maneja la respuesta
        System.out.println(response);
    }
}
