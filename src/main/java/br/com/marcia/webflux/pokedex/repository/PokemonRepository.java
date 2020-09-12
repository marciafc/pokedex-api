package br.com.marcia.webflux.pokedex.repository;

import br.com.marcia.webflux.pokedex.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokemonRepository extends ReactiveMongoRepository <Pokemon, String> {

}


