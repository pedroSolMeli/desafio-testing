package com.mercadolibre.desafiotesting.service;

import com.mercadolibre.desafiotesting.dto.CasaRequestDTO;
import com.mercadolibre.desafiotesting.dto.LocalidadeDTO;
import com.mercadolibre.desafiotesting.model.Casa;
import com.mercadolibre.desafiotesting.model.Localidade;
import com.mercadolibre.desafiotesting.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CasaService {

    @Autowired
    LocalidadeService localidadeService;

    @Autowired
    CasaRepository casaRepository;

    public List<Casa> buscarCasas() {
        List<Casa> casas = casaRepository.findAll();
        return casas;
    }

    public Casa buscarCasaPorId(Long id) {
        Casa casa = casaRepository.findById(id).orElse(null);

        if (casa == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Casa com id: " + id + " não encontrada");

        return casa;
    }

    public Casa criarCasa(CasaRequestDTO casaDTO) {
        Casa casaConvertida = convertToObject(casaDTO);
        Casa casa = casaRepository.save(casaConvertida);
        return casa;
    }

    public void removerCasaById(Long id) {
        this.buscarCasaPorId(id);
        casaRepository.deleteById(id);
    }

    private Casa convertToObject(CasaRequestDTO dto) {
        Localidade localidade = LocalidadeService.ConvertToObject(localidadeService.buscarPorId(dto.getLocalidadeId()));
        return Casa.builder().nome(dto.getNome()).comodos(ComodoService.Converte(dto.getComodos())).localidade(localidade).build();
    }

//
//    private CasaDTO convertToDto(Casa obj) {
//        return CasaDTO.builder().id(obj.getId()).nome(obj.getNome()).preco(obj.getPreco()).build();
//    }

}
