package com.mercadolibre.desafiotesting.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Casa implements Serializable {
	 
	private static final long serialVersionUID = -437715768496362763L;

}
