package com.mercadolibre.desafiotesting.dto;

import com.mercadolibre.desafiotesting.model.Comodo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CasaDTO {

    private String nome;
    private Long localidadeId;
    private List<Comodo> comodos;

}
