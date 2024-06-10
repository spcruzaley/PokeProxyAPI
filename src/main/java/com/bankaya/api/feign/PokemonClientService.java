package com.bankaya.api.feign;

import com.bankaya.schema.generated.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public class PokemonClientService {

    @Autowired
    private PokemonClient pokemonClient;

    @Autowired
    private RetryTemplate retryTemplate;

    @Cacheable("apiCache")
    public Root getPokemonByName(String pokemon) {
        return retryTemplate.execute(new RetryCallback<Root, RestClientException>() {
            @Override
            public Root doWithRetry(RetryContext context) throws RestClientException {
                return pokemonClient.getPokemonByName(pokemon);
            }
        });
    }
}
