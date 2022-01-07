package com.mercadolibre.desafiotesting.service;

import com.mercadolibre.desafiotesting.dto.CasaRequestDTO;
import com.mercadolibre.desafiotesting.dto.CasaResponseDTO;
import com.mercadolibre.desafiotesting.dto.ComodoRequestDTO;
import com.mercadolibre.desafiotesting.dto.LocalidadeDTO;
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

    @Mock
    LocalidadeService localidadeService;

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
    public void deveAcharMaiorComodoComSucesso(){

        //given
        List<Comodo> comodos = new ArrayList<>();

        Comodo banheiro = Comodo.builder().id(1L).nome("Banheiro").largura(2.0).comprimento(1.0).area(2.0).build();
        Comodo escritorio = Comodo.builder().id(2L).nome("Escritorio").largura(1.0).comprimento(1.0).area(1.0).build();
        comodos.add(banheiro);
        comodos.add(escritorio);

        Casa casa = Casa.builder().id(1L).nome("Residencia").comodos(comodos).areaTotal(12.0).build();

        //when
        Mockito.when(casaRepository.findById(1L)).thenReturn(Optional.ofNullable(casa));
        Comodo returnMaiorComodo = casaService.achaMaiorComodoDaCasa(1L);

        //then
        Assertions.assertEquals(returnMaiorComodo, banheiro);

    }


    @Test //TODO CRIAR CASA NAO ESTA FUNCIONANDO
    public void deveCriarCasaComSucesso(){

        //given
        List<ComodoRequestDTO> comodosDTO = new ArrayList<>();

        ComodoRequestDTO banheiroDTO = ComodoRequestDTO.builder().nome("Banheiro").largura(2.0).comprimento(1.0).build();
        ComodoRequestDTO escritorioDTO = ComodoRequestDTO.builder().nome("Escritorio").largura(1.0).comprimento(1.0).build();
        comodosDTO.add(banheiroDTO);
        comodosDTO.add(escritorioDTO);

        List<Comodo> comodos = new ArrayList<>();

        Comodo banheiro = Comodo.builder().nome("Banheiro").largura(2.0).comprimento(1.0).build();
        Comodo escritorio = Comodo.builder().nome("Escritorio").largura(1.0).comprimento(1.0).build();
        comodos.add(banheiro);
        comodos.add(escritorio);

        Localidade localidade = Localidade.builder().id(1L).nome("Morumbi").precoM2(new BigDecimal(50000)).build();
        LocalidadeDTO localidadeDTO = LocalidadeDTO.builder().id(1L).nome("Morumbi").precoM2(new BigDecimal(50000)).build();

        Casa casa = Casa.builder().id(1L).nome("Casa do Seu zé").localidade(localidade).comodos(comodos).build();
        CasaRequestDTO casaDTO = CasaRequestDTO.builder().nome("Casa do Seu zé").localidadeId(1L).comodos(comodosDTO).build();

        //when
        Mockito.when(localidadeService.buscarPorId(1L)).thenReturn(localidadeDTO);
        Mockito.when(casaRepository.save(casa)).thenReturn(casa);
        Casa casaRequest = casaService.criarCasa(casaDTO);

        //then
        Assertions.assertEquals(casaRequest.getNome(), casa.getNome());
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
