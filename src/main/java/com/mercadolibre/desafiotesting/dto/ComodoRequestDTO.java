package com.mercadolibre.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class ComodoRequestDTO {

    @NotBlank(message = "A nome do comodo é obrigatório")
    private String nome;

    @NotBlank(message = "A largura do comodo é obrigatório")
    private Double largura;

    @NotBlank(message = "A comprimento do comodo é obrigatório")
    private Double comprimento;

}
