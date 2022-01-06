package com.mercadolibre.desafiotesting.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadolibre.desafiotesting.dto.ComodoRequestDTO;
import com.mercadolibre.desafiotesting.dto.ComodoResponseDTO;
import com.mercadolibre.desafiotesting.model.Comodo;
import com.mercadolibre.desafiotesting.model.Localidade;
import com.mercadolibre.desafiotesting.repository.ComodoRepository;

@ExtendWith(MockitoExtension.class)
public class ComodoServiceTest {

	@Mock
	ComodoRepository comodoRepository;

	@InjectMocks
	ComodoService comodoService;

	@Test
	public void deveRetornarCalculodeArea() {
		// when
		double areaComodo = ComodoService.CalculaAreaComodo(2.0, 2.0);
		// then
		Assert.assertTrue(4.0 == areaComodo);
	}
	
	
	@Test
	public void deveConverterUmComodoRequestDtoParaComodo() {
		// given
		ComodoRequestDTO comodoDto = new ComodoRequestDTO("quarto",3.0,3.0);
		// when 
		Comodo comodoObjeto = ComodoService.ConverteComodoRequestDtoParaComodo(comodoDto);
		//then
		Assert.assertEquals(comodoObjeto.getComprimento(), comodoDto.getComprimento());
		Assert.assertEquals(comodoObjeto.getLargura(), comodoDto.getLargura());
		Assert.assertEquals(comodoObjeto.getNome(), comodoDto.getNome());
	}
	
	
	@Test
	public void deveConverterUmaListaComodoObjetoParaListaComodoDto() {
		// given
		Comodo comodoObjeto = new Comodo(1L,"quarto",3.0,3.0,9.0);
		Comodo comodoObjeto2 = new Comodo(2L,"quarto2",3.0,3.0,9.0);

        List<Comodo>  lista = new ArrayList();
        lista.add(comodoObjeto);
        lista.add(comodoObjeto2);

		// when 
		List<ComodoResponseDTO> listComodoDto = ComodoService.ConverteParaDTO(lista, 1l);
		//then
		Assert.assertTrue(!listComodoDto.isEmpty());
		Assert.assertEquals(listComodoDto.size(), lista.size());
		Assert.assertEquals(listComodoDto.get(0).getNome(), lista.get(0).getNome());
		
		
	}

	
	
	
	
	
}
