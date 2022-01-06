package com.mercadolibre.desafiotesting.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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

}
