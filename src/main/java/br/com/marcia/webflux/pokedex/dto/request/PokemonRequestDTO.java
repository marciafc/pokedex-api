package br.com.marcia.webflux.pokedex.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonRequestDTO {

    private String nome;

    private String categoria;

    private String habilidades;

    private Double peso;

}
