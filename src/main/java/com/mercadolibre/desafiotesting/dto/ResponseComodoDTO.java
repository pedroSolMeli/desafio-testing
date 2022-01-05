package com.mercadolibre.desafiotesting.dto;

import com.mercadolibre.desafiotesting.model.Comodo;

import lombok.Data;

@Data
public class ResponseComodoDTO {
	  private String nome;
	    private Double largura;
	    private Double comprimento;
	    private Double totalArea;
	    
	    
	    
		public ResponseComodoDTO(Comodo comodo) {
			this.nome = comodo.getNome();
			this.largura = comodo.getLargura();
			this.comprimento = comodo.getComprimento();
			this.totalArea = comodo.getComprimento()*comodo.getLargura();
		}
	    
	    
	    
	    

}
