package br.com.marcia.webflux.pokedex.service;

import static org.mockito.Mockito.when;

import br.com.marcia.webflux.pokedex.dto.request.PokemonRequestDTO;
import br.com.marcia.webflux.pokedex.dto.response.PokemonResponseDTO;
import br.com.marcia.webflux.pokedex.mapper.PokemonMapper;
import br.com.marcia.webflux.pokedex.model.Pokemon;
import br.com.marcia.webflux.pokedex.repository.PokemonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class PokemonServiceTest {

    private final Pokemon bulbassauro = Pokemon.builder().nome("Bulbassauro").categoria("Semente").habilidades("OverGrow").peso(6.09).build();
    private final Pokemon charizard = Pokemon.builder().nome("Charizard").categoria("Fogo").habilidades("Blaze").peso(90.05).build();
    private final Pokemon caterpie = Pokemon.builder().nome("Caterpie").categoria("Minhoca").habilidades("Poeira do Escudo").peso(2.09).build();

    private final PokemonRequestDTO bulbassauroRequestDTO = PokemonRequestDTO.builder().nome("Bulbassauro").categoria("Semente").habilidades("OverGrow").peso(6.09).build();

    @InjectMocks
    private PokemonService service;

    @Mock
    private PokemonRepository repository;

    private final PokemonMapper mapper = PokemonMapper.INSTANCE;

    @Test
    void getAll() {

        when(repository.findAll()).thenReturn(Flux.just(bulbassauro, charizard, caterpie));

        Flux<PokemonResponseDTO> actual = service.getAll();

        assertResults(actual, mapper.toDTO(bulbassauro), mapper.toDTO(charizard), mapper.toDTO(caterpie));
    }


    @Test
    void getByIdWhenIdExistsReturnCorrectPokemon() {

        when(repository.findById(bulbassauro.getId())).thenReturn(Mono.just(bulbassauro));

        Mono<PokemonResponseDTO> actual = service.getById(bulbassauro.getId());

        assertResults(actual, mapper.toDTO(bulbassauro));
    }

    @Test
    void getByIdWhenIdNotExistReturnEmptyMono() {

        when(repository.findById(bulbassauro.getId())).thenReturn(Mono.empty());

        Mono<PokemonResponseDTO> actual = service.getById(bulbassauro.getId());

        assertResults(actual);
    }


    @Test
    void save() {

        when(repository.save(bulbassauro)).thenReturn(Mono.just(bulbassauro));

        Mono<PokemonResponseDTO> actual = service.save(bulbassauroRequestDTO);

        assertResults(actual, mapper.toDTO(bulbassauro));
    }


    @Test
    void updateWhenIdExistsReturnUpdatedPokemon() {

        when(repository.findById(bulbassauro.getId())).thenReturn(Mono.just(bulbassauro));
        when(repository.save(bulbassauro)).thenReturn(Mono.just(bulbassauro));

        Mono<PokemonResponseDTO> actual = service.update(bulbassauro.getId(), bulbassauroRequestDTO);

        assertResults(actual, mapper.toDTO(bulbassauro));
    }

    @Test
    void updateWhenIdNotExistReturnEmptyMono() {

        when(repository.findById(bulbassauro.getId())).thenReturn(Mono.empty());

        Mono<PokemonResponseDTO> actual = service.update(bulbassauro.getId(), bulbassauroRequestDTO);

        assertResults(actual);
    }

    @Test
    void deleteWhenPokemonExistsPerformDeletion() {

        when(repository.findById(bulbassauro.getId())).thenReturn(Mono.just(bulbassauro));
        when(repository.delete(bulbassauro)).thenReturn(Mono.empty());

        Mono<Boolean> actual = service.delete(bulbassauro.getId());

        StepVerifier
                .create(actual)
                .expectNext(Boolean.TRUE)
                .verifyComplete();

    }

    @Test
    void deleteWhenIdNotExistReturnEmptyMono() {

        when(repository.findById(bulbassauro.getId())).thenReturn(Mono.empty());

        Mono<Boolean> actual = service.delete(bulbassauro.getId());

        StepVerifier
                .create(actual)
                .expectNext(Boolean.FALSE)
                .verifyComplete();
    }

    private void assertResults(Publisher<PokemonResponseDTO> publisher, PokemonResponseDTO... expectedProducts) {
        StepVerifier
                .create(publisher)
                .expectNext(expectedProducts)
                .verifyComplete();
    }


}
