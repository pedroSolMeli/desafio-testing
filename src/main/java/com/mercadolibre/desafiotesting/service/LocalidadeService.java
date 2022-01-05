package com.mercadolibre.desafiotesting.service;

import com.mercadolibre.desafiotesting.model.Localidade;
import com.mercadolibre.desafiotesting.repository.LocalidadeRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadeService {

    @Autowired
    LocalidadeRepository localidadeRepository;

    public List<Localidade> findAll() {
        List<Localidade> result = localidadeRepository.findAll();
        return result;
    }

    public Localidade findById(Long id) {
        Localidade result = localidadeRepository.findById(id).orElse(null);
        return result;
    }

    public Localidade create(Localidade localidade) {
        Localidade result = localidadeRepository.save(localidade);
        return result;
    }

    public Localidade update(Localidade localidade) {
        Localidade result = localidadeRepository.save(localidade);
        return result;
    }

    public Localidade updateById(Long id, Localidade localidade) {
        Localidade novaLocalidade = localidadeRepository.getById(id);
        novaLocalidade.setBairro(localidade.getBairro());
        novaLocalidade.setNome(localidade.getNome());
        novaLocalidade.setPreco(localidade.getPreco());
        Localidade result = localidadeRepository.save(novaLocalidade);
        return result;
    }

    public void delete(Localidade localidade) {
        localidadeRepository.delete(localidade);
    }

    public void deleteById(Long id) {
        localidadeRepository.deleteById(id);
    }


}
