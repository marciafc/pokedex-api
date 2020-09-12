package br.com.marcia.webflux.pokedex.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonResponseDTO {

    private String id;

    private String nome;

    private String categoria;

    private String habilidades;

    private Double peso;
}
