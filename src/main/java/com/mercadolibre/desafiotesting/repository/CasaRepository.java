package com.mercadolibre.desafiotesting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.desafiotesting.model.Casa;

@Repository
public interface CasaRepository extends JpaRepository<Casa, Long> {
}
