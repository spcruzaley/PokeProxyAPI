package com.bankaya.api.feign;

import com.bankaya.schema.generated.Root;
import feign.Param;
import feign.RequestLine;

public interface PokemonClient {

    @RequestLine("GET /pokemon/{name}")
    Root getPokemonByName(@Param("name") String name);
}
