package com.bankaya.webservice;

import com.bankaya.api.feign.PokemonClientService;
import com.bankaya.database.AuditInformationService;
import com.bankaya.schema.generated.Root;
import com.bankaya.webservice.gen.AbilitiesRequest;
import com.bankaya.webservice.gen.BaseExperienceRequest;
import com.bankaya.webservice.gen.GenericResponse;
import com.bankaya.webservice.gen.HeldItemsRequest;
import com.bankaya.webservice.gen.IdRequest;
import com.bankaya.webservice.gen.LocationAreaEncountersRequest;
import com.bankaya.webservice.gen.NameRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Slf4j
@Endpoint
public class PokemonEndpoint {

    private static final String NAMESPACE_URI = "http://bankaya.com/webservice/gen";
    private final StatusComponent statusComponent;
    private final PokemonClientService pokemonClientService;
    private final AuditInformationService auditInformationService;

    @Autowired
    public PokemonEndpoint(StatusComponent statusComponent, PokemonClientService pokemonClientService,
                           AuditInformationService auditInformationService) {
        this.pokemonClientService = pokemonClientService;
        this.statusComponent = statusComponent;
        this.auditInformationService = auditInformationService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "abilitiesRequest")
    @ResponsePayload
    public GenericResponse getAbilities(@RequestPayload AbilitiesRequest request) {
        Root pokemonByName = getPokemonData(request.getPokemon());
        auditInformationService.save("abilities");

        return genericResponse(pokemonByName.getAbilities());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "base_experienceRequest")
    @ResponsePayload
    public GenericResponse getBaseExperience(@RequestPayload BaseExperienceRequest request) {
        Root pokemonByName = getPokemonData(request.getPokemon());
        auditInformationService.save("base_experience");

        return genericResponse(pokemonByName.getBaseExperience());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "held_itemsRequest")
    @ResponsePayload
    public GenericResponse getHeldItems(@RequestPayload HeldItemsRequest request) {
        Root pokemonByName = getPokemonData(request.getPokemon());
        auditInformationService.save("held_items");

        return genericResponse(pokemonByName.getHeldItems());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "idRequest")
    @ResponsePayload
    public GenericResponse getId(@RequestPayload IdRequest request) {
        Root pokemonByName = getPokemonData(request.getPokemon());
        auditInformationService.save("id");

        return genericResponse(pokemonByName.getId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "nameRequest")
    @ResponsePayload
    public GenericResponse getName(@RequestPayload NameRequest request) {
        Root pokemonByName = getPokemonData(request.getPokemon());
        auditInformationService.save("name");

        return genericResponse(pokemonByName.getName());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "location_area_encountersRequest")
    @ResponsePayload
    public GenericResponse getLocationAreaEncounters(@RequestPayload LocationAreaEncountersRequest request) {
        Root pokemonByName = getPokemonData(request.getPokemon());
        auditInformationService.save("location_area_encounters");

        return genericResponse(pokemonByName.getLocationAreaEncounters());
    }

    private Root getPokemonData(String pokemon) {
        log.info("Setting abilities for {}", pokemon);

        return pokemonClientService.getPokemonByName(pokemon);
    }

    private GenericResponse genericResponse(Object heldItems) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setStatus(statusComponent.setDefaultStatus(heldItems));

        return genericResponse;
    }
}
