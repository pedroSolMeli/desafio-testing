package com.mercadolibre.desafiotesting.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CasaResponseDTO {

    private Long id;
    private String nome;
    private LocalidadeDTO bairro;
    private List<ComodoResponseDTO> comodos;
    private Double areaTotal;
    private BigDecimal preco;

}