package com.mercadolibre.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class CasaRequestDTO {

    @NotBlank(message = "A nome da casa é obrigatório")
    private String nome;

    @NotNull(message = "A localidade id da casa é obrigatório")
    private Long localidadeId;

    private List<ComodoRequestDTO> comodos;

}
