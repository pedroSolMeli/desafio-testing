package com.mercadolibre.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
public class CasaRequestDTO {

    @NotBlank(message = "A nome da casa estar vazio")
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres")
    @Pattern(regexp = "^([A-Z]{1})([a-z]{1,})$", message = "O nome da propriedade deve começar com letra maiúscula")
    private String nome;

    @NotNull(message = "A localidade id da casa é obrigatório")
    private Long localidadeId;

    @Valid
    private List<ComodoRequestDTO> comodos;

}
