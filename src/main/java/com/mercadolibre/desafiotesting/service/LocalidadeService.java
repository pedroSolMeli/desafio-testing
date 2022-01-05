package com.mercadolibre.desafiotesting.service;

import com.mercadolibre.desafiotesting.model.Localidade;
import com.mercadolibre.desafiotesting.repository.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadeService {

    @Autowired
    LocalidadeRepository localidadeRepository;

    public List<Localidade> findAll(){
        List<Localidade> result = localidadeRepository.findAll();
        return result;
    }

    public Localidade findById(Long id){
        Localidade result = localidadeRepository.findById(id).orElse(null);
        return result;
    }

    public Localidade create(Localidade localidade){
        Localidade result = localidadeRepository.save(localidade);
        return result;
    }

}
