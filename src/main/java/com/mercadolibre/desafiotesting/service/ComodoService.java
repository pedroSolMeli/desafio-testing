package com.mercadolibre.desafiotesting.service;

import com.mercadolibre.desafiotesting.dto.ComodoRequestDTO;
import com.mercadolibre.desafiotesting.dto.ComodoResponseDTO;
import com.mercadolibre.desafiotesting.model.Comodo;
import com.mercadolibre.desafiotesting.repository.ComodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ComodoService {
    @Autowired
    ComodoRepository repository;

    @Autowired
    CasaService casaService;

    private static double CalculaAreaComodo(double largura, double comprimento) {
        double area = largura * comprimento;
        return area;
    }

    public static Comodo ConverteComodoRequestDtoParaComodo(ComodoRequestDTO comodo) {
        return Comodo.builder()
                .nome(comodo.getNome())
                .comprimento(comodo.getComprimento())
                .largura(comodo.getLargura())
                .area(CalculaAreaComodo(comodo.getLargura(), comodo.getComprimento()))
                .build();
    }

    public static List<Comodo> ConverteComodoRequestDtoParaComodo(List<ComodoRequestDTO> comodos) {
        return comodos.stream().map(c -> ConverteComodoRequestDtoParaComodo(c)).collect(Collectors.toList());
    }

    public static ComodoResponseDTO ConverteParaDto(Comodo comodo,  Long maiorComodo) {
        boolean maior = comodo.getId().equals(maiorComodo) ? true: false;

        return ComodoResponseDTO.builder()
                .nome(comodo.getNome())
                .area(comodo.getArea())
                .maiorComodo(maior)
                .build();
    }

    public static List<ComodoResponseDTO> ConverteParaDTO(List<Comodo> obj, Long maiorComodo){
        return obj.stream().map(c -> ConverteParaDto(c, maiorComodo)).collect(Collectors.toList());
    }

}
