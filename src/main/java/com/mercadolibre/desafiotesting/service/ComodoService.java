package com.mercadolibre.desafiotesting.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.desafiotesting.dto.ComodoRequestDTO;
import com.mercadolibre.desafiotesting.dto.ComodoResponseDTO;
import com.mercadolibre.desafiotesting.model.Comodo;
import com.mercadolibre.desafiotesting.repository.CasaRepository;
import com.mercadolibre.desafiotesting.repository.ComodoRepository;


@Service
public class ComodoService {
	@Autowired
	ComodoRepository repository;

	@Autowired
	CasaRepository repositoryCasa;

	private List<ComodoResponseDTO> comodosdto;

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

	public List<ComodoResponseDTO> listarComodosETamanhos(Long id) {
		if (VerificaIdCasa(id)) {
			List<Comodo> comodos = repositoryCasa.getById(id).getComodos();
			comodosdto = null;
			for (Comodo comodo : comodos) {
				ComodoResponseDTO comodoDto = new ComodoResponseDTO(comodo);
				comodosdto.add(comodoDto);
			}
			return comodosdto;

		}
		return null;

	}

	private Boolean VerificaIdCasa(Long id) {
		return repositoryCasa.existsById(id);
	}
	
	
	public static Comodo Converte(ComodoRequestDTO comodo) {
		return Comodo.builder()
			.nome(comodo.getNome())
			.comprimento(comodo.getComprimento())
			.largura(comodo.getLargura())
			.build();
	}
	
	public static List<Comodo> Converte(List<ComodoRequestDTO> comodos){
		return comodos.stream().map(c -> Converte(c)).collect(Collectors.toList());
	}

}
