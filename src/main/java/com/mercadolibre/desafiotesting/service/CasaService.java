package com.mercadolibre.desafiotesting.service;

import com.mercadolibre.desafiotesting.dto.CasaDTO;
import com.mercadolibre.desafiotesting.model.Casa;
import com.mercadolibre.desafiotesting.repository.CasaRepository;
import com.mercadolibre.desafiotesting.repository.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class CasaService {

    @Autowired
    CasaRepository casaRepository;

    public List<Casa> buscarCasas(){
        List<Casa> casas = casaRepository.findAll();
        return casas;
    }

    public Casa buscarCasaPorId(Long id){
        Casa casa = casaRepository.getById(id);
        return casa;
    }

    public Casa criarCasa(Casa casaDTO){
        Casa casa = casaRepository.save(casaDTO);
        return casa;
    }

    public void removerCasa(Long id){
        casaRepository.deleteById(id);
    }


    private Casa convertToObject(CasaDTO dto) {
        return Casa.builder().nome(dto.getNome()).comodos(dto.getComodos()).localidade(new );
    }
//
//    private CasaDTO convertToDto(Casa obj) {
//        return CasaDTO.builder().id(obj.getId()).nome(obj.getNome()).preco(obj.getPreco()).build();
//    }

}
