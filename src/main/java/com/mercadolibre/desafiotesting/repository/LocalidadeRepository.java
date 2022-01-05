package com.mercadolibre.desafiotesting.repository;

import com.mercadolibre.desafiotesting.model.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, Long> {

}
