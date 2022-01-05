package com.mercadolibre.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
public class CasaRequestDTO {

    @NotBlank(message = "A nome da casa é obrigatório")
    private String nome;

    @NotBlank(message = "O id da localidade é obrigatório")
    private Long localidadeId;

    private List<ComodoRequestDTO> comodos;

}
