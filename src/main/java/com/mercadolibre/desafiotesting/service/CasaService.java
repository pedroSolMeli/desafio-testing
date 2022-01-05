package com.mercadolibre.desafiotesting.service;

import com.mercadolibre.desafiotesting.dto.CasaDTO;
import com.mercadolibre.desafiotesting.model.Casa;
import com.mercadolibre.desafiotesting.model.Localidade;
import com.mercadolibre.desafiotesting.repository.CasaRepository;
import com.mercadolibre.desafiotesting.repository.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class CasaService {

    @Autowired
    LocalidadeService localidadeService;

    @Autowired
    CasaRepository casaRepository;

    public List<Casa> buscarCasas(){
        List<Casa> casas = casaRepository.findAll();
        return casas;
    }

    public Casa buscarCasaPorId(Long id){
        Casa casa = casaRepository.getById(id);

        if (casa == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Localidade with id: " + id + " not found");

        return casa;
    }

    public Casa criarCasa(CasaDTO casaDTO){
        Casa casaConvertida = convertToObject(casaDTO);
        Casa casa = casaRepository.save(casaConvertida);
        return casa;
    }

    public void removerCasaById(Long id){
        Casa casa = casaRepository.getById(id);
        if (casa == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Casa with id: " + id + " not found");

        casaRepository.deleteById(id);
    }


    private Casa convertToObject(CasaDTO dto) {
        Localidade localidade = localidadeService.findById(dto.getLocalidadeId());
        return Casa.builder().nome(dto.getNome()).comodos(dto.getComodos()).localidade(localidade).build();
    }
//
//    private CasaDTO convertToDto(Casa obj) {
//        return CasaDTO.builder().id(obj.getId()).nome(obj.getNome()).preco(obj.getPreco()).build();
//    }

}
