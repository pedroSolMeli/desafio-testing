package com.mercadolibre.desafiotesting.service;

import com.mercadolibre.desafiotesting.controller.LocalidadeController;
import com.mercadolibre.desafiotesting.dto.LocalidadeDTO;
import com.mercadolibre.desafiotesting.model.Localidade;
import com.mercadolibre.desafiotesting.repository.LocalidadeRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class LocalidadeServiceTest {

    @Mock
    LocalidadeRepository localidadeRepository;

    @InjectMocks
    LocalidadeService localidadeService;

    @Test
    public void deveRetornarTodasAsCasaComSucesso(){
        Localidade localidade = new Localidade();
        localidade.setId(1l);
        localidade.setNome("Santos");
        localidade.setPrecoM2(BigDecimal.valueOf(5000));

        List<Localidade>  lista = new ArrayList();
        lista.add(localidade);

        //when
        Mockito.when(localidadeRepository.findAll()).thenReturn(lista);
        List<LocalidadeDTO> localidadeDTOS = localidadeService.buscarTodos();

        //then
        Assert.assertEquals(localidadeDTOS.size(), 1);
        Assert.assertEquals(localidadeDTOS.get(0).getId(), localidade.getId());
        Assert.assertEquals(localidadeDTOS.get(0).getNome(), localidade.getNome());
        Assert.assertEquals(localidadeDTOS.get(0).getPrecoM2(), localidade.getPrecoM2());
    }

//    @Test
//    public void deveRetornarACasaPorIdComSucesso(){
//        Localidade localidade = new Localidade();
//        localidade.setId(1l);
//        localidade.setNome("Santos");
//        localidade.setPrecoM2(BigDecimal.valueOf(5000));
//
//        List<Localidade>  lista = new ArrayList();
//        lista.add(localidade);
//
//        //when
//        Mockito.when(localidadeRepository.findAll()).thenReturn(lista);
//        List<LocalidadeDTO> localidadeDTOS = localidadeService.buscarTodos();
//
//        //then
//    }

}
