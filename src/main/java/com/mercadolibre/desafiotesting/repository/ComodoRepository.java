package com.mercadolibre.desafiotesting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.desafiotesting.model.Comodo;


@Repository
public interface ComodoRepository extends JpaRepository<Comodo, Long> {
	//List<Interessado> findAllByNuIdentificacao(String nuIdentificacao);
//	List<Comodo> findAllByIdCasa(Long id);
}
