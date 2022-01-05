package com.mercadolibre.desafiotesting.controller;

import com.mercadolibre.desafiotesting.model.Localidade;
import com.mercadolibre.desafiotesting.service.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.List;

@RestController
@RequestMapping("/localidade")
public class LocalidadeController {

    @Autowired
    LocalidadeService localidadeService;

    @GetMapping()
    public ResponseEntity<?> findAll(){
        List<Localidade> result = localidadeService.findAll();

        return ResponseEntity.ok(result);
    }

}
