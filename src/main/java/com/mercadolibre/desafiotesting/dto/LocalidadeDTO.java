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

    @NotBlank(message = "Não pode ser vazio")
    @Size(min = 5, max = 45, message = "Precisa ter entre 5 e 45 caracteres")
    private String nome;

    @NotNull(message = "Não pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor minimo é 0.0")
    @Digits(integer=19, fraction=2, message = "Não pode ter mais que 19 caracteres e 2 decimais")
    private BigDecimal precoM2;

    public LocalidadeDTO convertToDto(Localidade obj) {
        return LocalidadeDTO.builder().id(obj.getId()).nome(obj.getNome()).precoM2(obj.getPrecoM2()).build();
    }

    public Localidade convertToObject(LocalidadeDTO dto) {
        return Localidade.builder().nome(dto.getNome()).precoM2(dto.getPrecoM2()).build();
    }

}