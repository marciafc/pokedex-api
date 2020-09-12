package br.com.marcia.webflux.pokedex;

import br.com.marcia.webflux.pokedex.model.Pokemon;
import br.com.marcia.webflux.pokedex.repository.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;


@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args);
	}

	@Bean
	CommandLineRunner init (ReactiveMongoOperations operations, PokemonRepository repository) {

		return args -> {

			Flux<Pokemon> pokemonFlux = Flux.just(
					Pokemon.builder().nome("Bulbassauro").categoria("Semente").habilidades("OverGrow").peso(6.09).build(),
					Pokemon.builder().nome("Charizard").categoria("Fogo").habilidades("Blaze").peso(90.05).build(),
					Pokemon.builder().nome("Caterpie").categoria("Minhoca").habilidades("Poeira do Escudo").peso(2.09).build(),
					Pokemon.builder().nome("Blastoise").categoria("Marisco").habilidades("Torrente").peso(6.09).build())
					.flatMap(repository::save);

			pokemonFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);
		};
	}

}
