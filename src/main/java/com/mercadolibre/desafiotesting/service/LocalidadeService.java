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

    public List<LocalidadeDTO> buscarTodos() {
        List<Localidade> localidades = localidadeRepository.findAll();
        List<LocalidadeDTO> result = new ArrayList<>();
        for (Localidade l : localidades) {
            result.add(ConvertToDto(l));
        }
        return result;
    }

    public LocalidadeDTO buscarPorId(Long id) {
        Localidade localidade = localidadeRepository.findById(id).orElse(null);

        if (localidade == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Localidade with id: " + id + " not found");

        LocalidadeDTO result = ConvertToDto(localidade);
        return result;
    }

    public LocalidadeDTO criar(LocalidadeDTO localidadeDTO) {
        Localidade novaLocalidade = ConvertToObject(localidadeDTO);
        Localidade localidade = localidadeRepository.save(novaLocalidade);
        LocalidadeDTO result = ConvertToDto(localidade);

        return result;
    }

    public LocalidadeDTO atualizarPorId(Long id, LocalidadeDTO localidadeDTO) {
        Localidade novaLocalidade = ConvertToObject(this.buscarPorId(id));

        if (novaLocalidade == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Localidade with id: " + id + " not found");

        novaLocalidade.setNome(localidadeDTO.getNome());
        novaLocalidade.setPrecoM2(localidadeDTO.getPrecoM2());

        Localidade localidadeAtualizada = localidadeRepository.save(novaLocalidade);
        LocalidadeDTO result = ConvertToDto(localidadeAtualizada);

        return result;
    }

    public void apagarPorId(Long id) {
        this.buscarPorId(id);
        localidadeRepository.deleteById(id);
    }

    public static LocalidadeDTO ConvertToDto(Localidade obj) {
        return LocalidadeDTO.builder().id(obj.getId()).nome(obj.getNome()).precoM2(obj.getPrecoM2()).build();
    }

    public static Localidade ConvertToObject(LocalidadeDTO dto) {
        return Localidade.builder().id(dto.getId()).nome(dto.getNome()).precoM2(dto.getPrecoM2()).build();
    }

}
