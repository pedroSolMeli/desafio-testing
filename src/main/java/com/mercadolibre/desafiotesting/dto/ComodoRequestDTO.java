package com.mercadolibre.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class ComodoRequestDTO {

    @NotBlank(message = "O campo não pode estar vazio.")
    @Size(min = 3, max = 30, message = "O comprimento do nome não pode exceder 30 caracteres")
    @Pattern(regexp = "^([A-Z]{1})([a-z]{1,})$", message = "O nome do comodo deve começar com letra maiúscula")
    private String nome;

    @NotNull(message = "A largura do cômodo não pode estar vazia.")
    @Max(value = 25, message = "A largura máximma permitida por cômodo é de 25 metros.")
    @Min(value = 0, message = "A largura mínima é 0")
    private Double largura;

    @NotNull(message = " O comprimento do cômodo não pode estar vazio.")
    @Max(value = 33, message = "O comprimento máximo permitido por cômodo é de 33 metros.")
    @Min(value = 0, message = "O comprimento mínimo é 0")
    private Double comprimento;

}
