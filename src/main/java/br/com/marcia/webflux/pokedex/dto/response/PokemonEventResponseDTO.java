package br.com.marcia.webflux.pokedex.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonEventResponseDTO {

    private Long eventId;

    private String eventType;

}
