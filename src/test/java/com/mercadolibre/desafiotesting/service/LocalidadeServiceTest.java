package com.mercadolibre.desafiotesting.service;

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
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class LocalidadeServiceTest {

    @Mock
    LocalidadeRepository localidadeRepository;

    @InjectMocks
    LocalidadeService localidadeService;

    @Test
    public void deveRetornarTodasAsLocalidadeComSucesso(){
        Localidade localidade = new Localidade(1l, "Santos", BigDecimal.valueOf(5000));

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

    @Test
    public void deveRetornarALocalidadePorIdComSucesso(){
        Localidade localidade = new Localidade(1l, "Santos", BigDecimal.valueOf(5000));

        //when
        Mockito.when(localidadeRepository.findById(1l)).thenReturn(Optional.of(localidade));
        LocalidadeDTO localidadeDTOS = localidadeService.buscarPorId(1l);

        //then
        Assert.assertEquals(localidadeDTOS.getId(), localidade.getId());
        Assert.assertEquals(localidadeDTOS.getNome(), localidade.getNome());
        Assert.assertEquals(localidadeDTOS.getPrecoM2(), localidade.getPrecoM2());
    }


    @Test
    public void deveRetornarALocalidadePorIdSemSucesso(){
        Assert.assertThrows(ResponseStatusException.class, () -> localidadeService.buscarPorId(1l));
    }

    @Test
    public void deveCriarALocalidadeComSucesso(){
        Localidade localidade = new Localidade(1l, "Santos", BigDecimal.valueOf(5000));
        LocalidadeDTO localidadeDTO = new LocalidadeDTO(1l, "Santos", BigDecimal.valueOf(5000));

        //when
        Mockito.when(localidadeRepository.save(localidade)).thenReturn(localidade);
        LocalidadeDTO localidadeDTOS = localidadeService.criar(localidadeDTO);

        //then
        Assert.assertEquals(localidadeDTOS.getId(), localidade.getId());
        Assert.assertEquals(localidadeDTOS.getNome(), localidade.getNome());
        Assert.assertEquals(localidadeDTOS.getPrecoM2(), localidade.getPrecoM2());
    }

    @Test
    public void deveAtualizarALocalidadeComSucesso(){
        Localidade localidade = new Localidade(1l, "Santos", BigDecimal.valueOf(5000));
        LocalidadeDTO localidadeDTO = new LocalidadeDTO(1l, "Santos", BigDecimal.valueOf(5000));

        //when
        Mockito.when(localidadeRepository.findById(1l)).thenReturn(Optional.of(localidade));
        Mockito.when(localidadeRepository.save(localidade)).thenReturn(localidade);
        LocalidadeDTO localidadeDTOS = localidadeService.atualizarPorId(1l, localidadeDTO);

        //then
        Assert.assertEquals(localidadeDTOS.getId(), localidade.getId());
        Assert.assertEquals(localidadeDTOS.getNome(), localidade.getNome());
        Assert.assertEquals(localidadeDTOS.getPrecoM2(), localidade.getPrecoM2());
    }

    @Test
    public void deveAtualizarALocalidadeSemSucesso(){
        LocalidadeDTO localidadeDTO = new LocalidadeDTO(1l, "Santos", BigDecimal.valueOf(5000));

        Assert.assertThrows(ResponseStatusException.class, () -> localidadeService.atualizarPorId(1l, localidadeDTO));
    }

    @Test
    public void deveDeletarALocalidadeComSucesso(){
        Localidade localidade = new Localidade(1l, "Santos", BigDecimal.valueOf(5000));

        //when
        Mockito.when(localidadeRepository.findById(1l)).thenReturn(Optional.of(localidade));
        Mockito.doNothing().when(localidadeRepository).deleteById(1l);
        localidadeService.apagarPorId(1l);
    }

}
