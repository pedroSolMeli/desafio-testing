package com.mercadolibre.desafiotesting.dto;

import com.mercadolibre.desafiotesting.model.Comodo;
import com.mercadolibre.desafiotesting.service.ComodoService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Builder
public class ComodoResponseDTO {

	private String nome;
	private Double area;
	private boolean maiorComodo;

}
