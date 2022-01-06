package com.mercadolibre.desafiotesting.service;

import com.mercadolibre.desafiotesting.dto.CasaResponseDTO;
import com.mercadolibre.desafiotesting.model.Casa;
import com.mercadolibre.desafiotesting.model.Comodo;
import com.mercadolibre.desafiotesting.model.Localidade;
import com.mercadolibre.desafiotesting.repository.CasaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CasaServiceTest {

    @Mock
    CasaRepository casaRepository;

    @InjectMocks
    CasaService casaService;


    @Test
    public void deveRetornarTodasAsCasasComSucesso(){

        //given
        Localidade localidade = Localidade.builder().id(1L).nome("Morumbi").precoM2(new BigDecimal(50000)).build();
        List<Comodo> comodos = new ArrayList<>();

        Comodo banheiro = Comodo.builder().id(1L).nome("Banheiro").largura(2.0).comprimento(1.0).area(2.0).build();
        Comodo escritorio = Comodo.builder().id(1L).nome("Escritorio").largura(5.0).comprimento(2.0).area(10.0).build();
        comodos.add(banheiro);
        comodos.add(escritorio);

        Casa casa = Casa.builder().id(1L).nome("Residencia").localidade(localidade).comodos(comodos).areaTotal(12.0).build();
        List<Casa> casaLista = new ArrayList<>();
        casaLista.add(casa);

        //when
        Mockito.when(casaRepository.findAll()).thenReturn(casaLista);
        List<Casa> casaResponse = casaService.buscarCasas();

        //then
        Assertions.assertEquals(casaResponse.size(), 1);
        Assertions.assertEquals(casaResponse.get(0).getId(), casa.getId());
        Assertions.assertEquals(casaResponse.get(0).getNome(), casa.getNome());
        Assertions.assertEquals(casaResponse.get(0).getLocalidade(), casa.getLocalidade());
        Assertions.assertEquals(casaResponse.get(0).getComodos(), casa.getComodos());
        Assertions.assertEquals(casaResponse.get(0).getAreaTotal(), casa.getAreaTotal());

    }

    @Test
    public void deveRetornarCasaPorIdComSucesso(){

        //given
        Localidade localidade = Localidade.builder().id(1L).nome("Morumbi").precoM2(new BigDecimal(50000)).build();
        List<Comodo> comodos = new ArrayList<>();

        Comodo banheiro = Comodo.builder().id(1L).nome("Banheiro").largura(2.0).comprimento(1.0).area(2.0).build();
        Comodo escritorio = Comodo.builder().id(1L).nome("Escritorio").largura(5.0).comprimento(2.0).area(10.0).build();
        comodos.add(banheiro);
        comodos.add(escritorio);

        Casa casa = Casa.builder().id(1L).nome("Residencia").localidade(localidade).comodos(comodos).areaTotal(12.0).build();

        //when
        Mockito.when(casaRepository.findById(1L)).thenReturn(Optional.ofNullable(casa));
        CasaResponseDTO casaResponseDTO = casaService.buscarCasaPorId(1L);

        //then

        Assertions.assertEquals(casaResponseDTO.getId(),casa.getId());
        Assertions.assertEquals(casaResponseDTO.getNome(), casa.getNome());
        Assertions.assertEquals(casaResponseDTO.getBairro().getNome(), casa.getLocalidade().getNome());
        Assertions.assertEquals(casaResponseDTO.getComodos().size(), casa.getComodos().size());
        Assertions.assertEquals(casaResponseDTO.getAreaTotal(), casa.getAreaTotal());


    }


    @Test
    public void deveRetornarAreaTotalDaPropriedadeComSucesso() {

        //given
        Localidade localidade = Localidade.builder().id(1L).nome("Morumbi").precoM2(new BigDecimal(50000)).build();
        List<Comodo> comodos = new ArrayList<>();

        Comodo banheiro = Comodo.builder().id(1L).nome("Banheiro").largura(2.0).comprimento(1.0).area(2.0).build();
        Comodo escritorio = Comodo.builder().id(1L).nome("Escritorio").largura(5.0).comprimento(2.0).area(10.0).build();
        comodos.add(banheiro);
        comodos.add(escritorio);

        Double areaTotal = casaService.calculaAreaTotal(comodos);
        Casa casa = Casa.builder().id(1L).nome("Residencia").localidade(localidade).comodos(comodos).areaTotal(areaTotal).build();

        //when
        Mockito.when(casaRepository.findById(1L)).thenReturn(Optional.ofNullable(casa));
        CasaResponseDTO responseDTO = casaService.buscarCasaPorId(1L);

        //then
        Assertions.assertEquals(responseDTO.getAreaTotal().doubleValue(), 12);

    }


}
