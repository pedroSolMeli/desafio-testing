package com.mercadolibre.desafiotesting.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComodoResponseDTO {

	private String nome;
	private Double area;
	private boolean maiorComodo;

}
