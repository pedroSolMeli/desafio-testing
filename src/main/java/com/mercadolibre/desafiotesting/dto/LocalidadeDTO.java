package com.mercadolibre.desafiotesting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.desafiotesting.model.Localidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalidadeDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Nome da localidade não pode estar vazio")
    @Size(max = 45, message = "Comprimento do nome da localidade não pode exceder 45 caracteres")
    private String nome;

    @NotNull(message = "PrecoM2 não pode estar vazio")
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor minimo é 0.0")
    @Digits(integer=13, fraction=2, message = "PrecoM2 não pode exceder 13 caracteres")
    private BigDecimal precoM2;

}