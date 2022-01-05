package com.mercadolibre.desafiotesting.service;

import com.mercadolibre.desafiotesting.model.Localidade;
import com.mercadolibre.desafiotesting.repository.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (result == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Localidade with id: " + id + " not found");
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
        if (novaLocalidade == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Localidade with id: " + id + " not found");
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
        Localidade result = localidadeRepository.getById(id);
        if (result == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Localidade with id: " + id + " not found");
        localidadeRepository.deleteById(id);
    }

}
