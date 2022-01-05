package com.mercadolibre.desafiotesting.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Casa")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Casa implements Serializable {
	 
	private static final long serialVersionUID = -437715768496362763L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Localidade localidade;
	//private List<Comodo> comodos;


}
