package com.mercadolibre.desafiotesting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.desafiotesting.dto.ComodoResponseDTO;
import com.mercadolibre.desafiotesting.model.Comodo;
import com.mercadolibre.desafiotesting.service.ComodoService;

@RestController
@RequestMapping("comodo/")
public class ComodoController {
	
	@Autowired
	ComodoService service;

	@GetMapping(value = "maiorcomodo" + "/{id}")
	public ResponseEntity<?> buscaMaiorComodoCasa(@PathVariable Long id){
		Comodo comodo = service.achaMaiorComodo(id);
		return ResponseEntity.ok(comodo);
	}

	@GetMapping(value = "tamanhocomodos" + "/{id}")
	public ResponseEntity<?> buscaListaComodosETamanhos(@PathVariable Long id){
		List<ComodoResponseDTO> comodos = service.listarComodosETamanhos(id);
		return ResponseEntity.ok(comodos);
	}
	
}
