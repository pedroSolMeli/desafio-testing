package com.mercadolibre.desafiotesting.service;

import com.mercadolibre.desafiotesting.dto.LocalidadeDTO;
import com.mercadolibre.desafiotesting.model.Localidade;
import com.mercadolibre.desafiotesting.repository.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalidadeService {

    @Autowired
    private LocalidadeRepository localidadeRepository;
    private LocalidadeDTO localidadeDTO = new LocalidadeDTO();

    public List<LocalidadeDTO> findAll() {

        List<Localidade> localidades = localidadeRepository.findAll();
        List<LocalidadeDTO> result = new ArrayList<>();
        for (Localidade l : localidades) {
            result.add(localidadeDTO.convertToDto(l));
        }
        return result;

    }

    public LocalidadeDTO findById(Long id) {

        Localidade localidade = localidadeRepository.findById(id).orElse(null);

        if (localidade == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Localidade with id: " + id + " not found");

        LocalidadeDTO result = localidadeDTO.convertToDto(localidade);
        return result;

    }

    public LocalidadeDTO create(LocalidadeDTO localidadeDTO) {

        Localidade novaLocalidade = localidadeDTO.convertToObject(localidadeDTO);
        Localidade localidade = localidadeRepository.save(novaLocalidade);
        LocalidadeDTO result = localidadeDTO.convertToDto(localidade);

        return result;

    }

    public LocalidadeDTO updateById(Long id, LocalidadeDTO localidadeDTO) {

        Localidade novaLocalidade = localidadeDTO.convertToObject(this.findById(id));

        if (novaLocalidade == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Localidade with id: " + id + " not found");

        novaLocalidade.setNome(localidadeDTO.getNome());
        novaLocalidade.setPrecoM2(localidadeDTO.getPrecoM2());

        Localidade localidadeAtualizada = localidadeRepository.save(novaLocalidade);
        LocalidadeDTO result = localidadeDTO.convertToDto(localidadeAtualizada);

        return result;
    }

    public void deleteById(Long id) {
        Localidade result = localidadeDTO.convertToObject(this.findById(id));
        if (result == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Localidade with id: " + id + " not found");
        localidadeRepository.deleteById(id);
    }


}
