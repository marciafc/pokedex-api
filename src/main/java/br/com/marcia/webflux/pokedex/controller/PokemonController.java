package br.com.marcia.webflux.pokedex.controller;

import br.com.marcia.webflux.pokedex.dto.request.PokemonRequestDTO;
import br.com.marcia.webflux.pokedex.dto.response.PokemonEventResponseDTO;
import br.com.marcia.webflux.pokedex.dto.response.PokemonResponseDTO;
import br.com.marcia.webflux.pokedex.service.PokemonService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pokemons")
@AllArgsConstructor
public class PokemonController {

    @NonNull
    private final PokemonService service;


    @GetMapping
    public Flux<PokemonResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PokemonResponseDTO>> getById(@PathVariable String id) {
        return service.getById(id)
                .map(pokemon -> ResponseEntity.ok(pokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PokemonResponseDTO> save(@RequestBody PokemonRequestDTO pokemonRequestDTO) {
        return service.save(pokemonRequestDTO);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<PokemonResponseDTO>> update(@PathVariable(value = "id") String id,
                                                          @RequestBody PokemonRequestDTO pokemonRequestDTO) {
        return service.update(id, pokemonRequestDTO)
                .map(updatePokemon -> ResponseEntity.ok(updatePokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable(value = "id") String id) {
        return service.delete(id)
                .map(removed -> removed ? ResponseEntity.noContent().build(): ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAll() {
        return service.deleteAll();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PokemonEventResponseDTO> getEvents() {
        return service.getEvents();
    }

}