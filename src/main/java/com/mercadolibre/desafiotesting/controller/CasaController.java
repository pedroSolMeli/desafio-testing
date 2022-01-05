package com.mercadolibre.desafiotesting.controller;

import com.mercadolibre.desafiotesting.dto.CasaRequestDTO;
import com.mercadolibre.desafiotesting.model.Casa;
import com.mercadolibre.desafiotesting.service.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/casa")
public class CasaController {

    @Autowired
    CasaService casaService;

    @GetMapping
    public ResponseEntity<?> buscarCasas(){
        List<Casa> casas = casaService.buscarCasas();
        return ResponseEntity.ok(casas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCasaPorId(@PathVariable Long id){
       Casa casa = casaService.buscarCasaPorId(id);
        return ResponseEntity.ok(casa);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> criarCasa(@Valid @RequestBody CasaRequestDTO casaDTO){
        Casa casa = casaService.criarCasa(casaDTO);
        return ResponseEntity.ok(casa);
    }

    @DeleteMapping()
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> removerCasa(@PathVariable Long id){
        casaService.removerCasaById(id);
        return new ResponseEntity<>("Deleted article id: " + id, HttpStatus.NO_CONTENT);
    }

}
