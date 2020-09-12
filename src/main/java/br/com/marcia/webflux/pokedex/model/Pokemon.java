package br.com.marcia.webflux.pokedex.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {

    @Id
    private String id;

    private String nome;

    private String categoria;

    private String habilidades;

    private Double peso;

}