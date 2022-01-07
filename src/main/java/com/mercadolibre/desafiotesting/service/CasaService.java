package com.mercadolibre.desafiotesting.service;

import com.mercadolibre.desafiotesting.dto.CasaRequestDTO;
import com.mercadolibre.desafiotesting.dto.CasaResponseDTO;
import com.mercadolibre.desafiotesting.dto.ComodoResponseDTO;
import com.mercadolibre.desafiotesting.dto.LocalidadeDTO;
import com.mercadolibre.desafiotesting.model.Casa;
import com.mercadolibre.desafiotesting.model.Comodo;
import com.mercadolibre.desafiotesting.model.Localidade;
import com.mercadolibre.desafiotesting.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
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

    public CasaResponseDTO buscarCasaPorId(Long id) {
        Casa casa = getCasa(id);

        CasaResponseDTO result = converteParaDto(casa);
        return result;
    }


    public Comodo achaMaiorComodoDaCasa(Long casaId) {
        Casa casa = getCasa(casaId);
        Comodo maiorComodo = Comodo.builder().area(0.0).build();
        List<Comodo> comodos = casa.getComodos();
        for (Comodo comodo : comodos) {
            if (comodo.getArea() > maiorComodo.getArea()) {
                maiorComodo = comodo;
            }
        }
        return maiorComodo;
    }


    public Casa criarCasa(CasaRequestDTO casaDTO) {
        Casa casaConvertida = convertToObject(casaDTO);
        Casa casa = casaRepository.save(casaConvertida);
        return casa;
    }

    public void removerCasaById(Long id) {
        this.getCasa(id);
        casaRepository.deleteById(id);
    }

    public Casa getCasa(Long id) {
        Casa casa = casaRepository.findById(id).orElse(null);
        if (casa == null) {
            ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatus.NOT_FOUND, "Casa com id: " + id + " não encontrada");
            throw responseStatusException;
        }
        return casa;
    }

    private Casa convertToObject(CasaRequestDTO dto) {

        LocalidadeDTO localidadeDTO = localidadeService.buscarPorId(dto.getLocalidadeId());

        Localidade localidade = LocalidadeService.ConvertToObject(localidadeDTO);
        List<Comodo> comodos = ComodoService.ConverteComodoRequestDtoParaComodo(dto.getComodos());
        Double areaTotal = calculaAreaTotal(comodos);
        return Casa.builder().nome(dto.getNome()).comodos(comodos).localidade(localidade).areaTotal(areaTotal).build();
    }

    protected Double calculaAreaTotal(List<Comodo> comodos) {
        Double area = 0.0;
        for (Comodo comodo : comodos) {
            area += comodo.getArea();
        }
        return area;
    }

    protected  BigDecimal calculaPreco(Double areaTotal, BigDecimal precoM2) {
        BigDecimal preco = precoM2.multiply(BigDecimal.valueOf(areaTotal));
        return preco;
    }

    private CasaResponseDTO converteParaDto(Casa casa) {
        LocalidadeDTO localidadeDTO = LocalidadeService.ConvertToDto(casa.getLocalidade());
        Comodo comodo = achaMaiorComodoDaCasa(casa.getId());
        List<ComodoResponseDTO> comodoResponseDTOS = ComodoService.ConverteParaDTO(casa.getComodos(), comodo.getId());
        BigDecimal preco = calculaPreco(casa.getAreaTotal(), casa.getLocalidade().getPrecoM2());

        return CasaResponseDTO.builder().id(casa.getId())
                .nome(casa.getNome())
                .bairro(localidadeDTO)
                .comodos(comodoResponseDTOS)
                .areaTotal(casa.getAreaTotal())
                .preco(preco)
                .build();
    }

}
