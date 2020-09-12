package br.com.marcia.webflux.pokedex.service;

import br.com.marcia.webflux.pokedex.dto.request.PokemonRequestDTO;
import br.com.marcia.webflux.pokedex.dto.response.PokemonEventResponseDTO;
import br.com.marcia.webflux.pokedex.dto.response.PokemonResponseDTO;
import br.com.marcia.webflux.pokedex.mapper.PokemonMapper;
import br.com.marcia.webflux.pokedex.repository.PokemonRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@AllArgsConstructor
public class PokemonService {

    @NonNull
    private final PokemonRepository repository;

    private final PokemonMapper pokemonMapper = PokemonMapper.INSTANCE;

    public Flux<PokemonResponseDTO> getAll() {
        return repository.findAll().flatMap(pokemon -> Mono.just(pokemonMapper.toDTO(pokemon)));
    }

    public Mono<PokemonResponseDTO> getById(String id) {
        return repository.findById(id).map(pokemon -> pokemonMapper.toDTO(pokemon));
    }

    public Mono<PokemonResponseDTO> save(PokemonRequestDTO pokemonRequestDTO) {
        return repository.save(pokemonMapper.toModel(pokemonRequestDTO))
                .map(savedPokemon -> pokemonMapper.toDTO(savedPokemon));
    }

    public Mono<PokemonResponseDTO> update(String id, PokemonRequestDTO pokemonRequestDTO) {
        return repository.findById(id)
                .map(existingPokemon -> existingPokemon.toBuilder()
                    .nome(pokemonRequestDTO.getNome())
                    .categoria(pokemonRequestDTO.getCategoria())
                    .habilidades(pokemonRequestDTO.getHabilidades())
                    .peso(pokemonRequestDTO.getPeso())
                        .build())

                    .flatMap(repository::save)
                            .map(savedPokemon -> pokemonMapper.toDTO(savedPokemon));
    }

    public Mono<Boolean> delete(String id) {
        return repository.findById(id)
                .flatMap(existingPokemon ->
                        repository.delete(existingPokemon)
                                .then(Mono.just(Boolean.TRUE))
                )
                .defaultIfEmpty(Boolean.FALSE);
    }

    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }

    public Flux<PokemonEventResponseDTO> getEvents() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(val ->
                        PokemonEventResponseDTO.builder()
                        .eventId(val)
                        .eventType("Pokemon Event")
                        .build()
                );
    }

}
