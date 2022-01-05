package com.mercadolibre.desafiotesting.dto;

import com.mercadolibre.desafiotesting.model.Comodo;
import com.mercadolibre.desafiotesting.model.Localidade;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
public class CasaDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Localidade localidade;
    private List<Comodo> comodos;

}
