package com.mercadolibre.desafiotesting.dto;

import java.math.BigDecimal;
import java.util.List;


public class CasaResponseDTO {

    private String nome;
    private LocalidadeDTO localidadeId;
    private List<ComodoResponseDTO> comodos;
    private Double areaTotal;
    private BigDecimal preco;

}