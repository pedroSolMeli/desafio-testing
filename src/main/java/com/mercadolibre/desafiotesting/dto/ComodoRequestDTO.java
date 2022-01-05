package com.mercadolibre.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class ComodoRequestDTO {

    @NotBlank(message = "O campo não pode estar vazio.")
    @Size(min = 3, max = 30, message = "O comprimento do nome não pode exceder 30 caracteres")
    //@Pattern(regexp = "(?=.*[A-Z])[\\p{Punct}A-Z0-9 ]{1,32}", message = "outro erro")
    private String nome;

    @NotEmpty(message = "A largura do cômodo não pode estar vazia.")
    @Max( value = 25, message = "A largura permitida por cômodo é de 25 metros.")
    private Double largura;

    @NotEmpty(message = " O comprimento do cômodo não pode estar vazio.")
    @Max( value = 25, message = "O comprimento permitido por cômodo é de 33 metros.")
    private Double comprimento;

}
