package com.mercadolibre.desafiotesting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mercadolibre.desafiotesting.dto.ResponseComodoDTO;
import com.mercadolibre.desafiotesting.model.Comodo;
import com.mercadolibre.desafiotesting.repository.CasaRepository;
import com.mercadolibre.desafiotesting.repository.ComodoRepository;

public class ComodoService {
	@Autowired
	ComodoRepository repository;

	@Autowired
	CasaRepository repositoryCasa;

	private List<ResponseComodoDTO> comodosdto;

	public Comodo achaMaiorComodo(Long id) {
		if (VerificaIdCasa(id)) {
			Comodo maiorComodo = null;
			Double maiorArea = (double) 0;
			List<Comodo> comodos = repositoryCasa.getById(id).getComodos();
			for (Comodo comodo : comodos) {
				Double tamanhoComodo = comodo.getComprimento() * comodo.getLargura();
				if (tamanhoComodo > maiorArea) {
					maiorComodo = comodo;
				}
			}
			return maiorComodo;
		}
		return null;
	}

	public List<ResponseComodoDTO> listarComodosETamanhos(Long id) {
		if (VerificaIdCasa(id)) {
			List<Comodo> comodos = repositoryCasa.getById(id).getComodos();
			comodosdto = null;
			for (Comodo comodo : comodos) {
				ResponseComodoDTO comodoDto = new ResponseComodoDTO(comodo);
				comodosdto.add(comodoDto);
			}
			return comodosdto;

		}
		return null;

	}

	private Boolean VerificaIdCasa(Long id) {
		return repositoryCasa.existsById(id);
	}

}
