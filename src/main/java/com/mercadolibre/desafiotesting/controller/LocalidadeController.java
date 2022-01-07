package com.mercadolibre.desafiotesting.controller;

import com.mercadolibre.desafiotesting.dto.LocalidadeDTO;
import com.mercadolibre.desafiotesting.service.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/localidade")
public class LocalidadeController {

    @Autowired
    LocalidadeService localidadeService;

    @GetMapping()
    public ResponseEntity<?> buscarLocalidades(){
        List<LocalidadeDTO> result = localidadeService.buscarTodos();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarLocalidadePorId(@PathVariable Long id){
        LocalidadeDTO result = localidadeService.buscarPorId(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> criarLocalidade(@Valid @RequestBody LocalidadeDTO localidadeDTO){
        LocalidadeDTO result = localidadeService.criar(localidadeDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> atualizarLocalidade(@PathVariable Long id, @RequestBody LocalidadeDTO localidadeDTO){
        localidadeService.atualizarPorId(id, localidadeDTO);
        return new ResponseEntity<>("Updated localidade id: " + id, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> deletarLocalidade(@PathVariable Long id){
        localidadeService.apagarPorId(id);
        return new ResponseEntity<>("Deleted localidade id: " + id,HttpStatus.OK);
    }

}
