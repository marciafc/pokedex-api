package br.com.marcia.webflux.pokedex.mapper;

import br.com.marcia.webflux.pokedex.dto.request.PokemonRequestDTO;
import br.com.marcia.webflux.pokedex.dto.response.PokemonResponseDTO;
import br.com.marcia.webflux.pokedex.model.Pokemon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PokemonMapper {

    PokemonMapper INSTANCE = Mappers.getMapper(PokemonMapper.class);

    Pokemon toModel(PokemonRequestDTO pokemonRequestDTO);

    PokemonResponseDTO toDTO(Pokemon pokemon);

}
