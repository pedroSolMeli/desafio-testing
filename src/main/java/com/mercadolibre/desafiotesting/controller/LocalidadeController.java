package com.mercadolibre.desafiotesting.controller;

import com.mercadolibre.desafiotesting.model.Localidade;
import com.mercadolibre.desafiotesting.service.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Localidade result = localidadeService.findById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Localidade localidade){
        Localidade result = localidadeService.create(localidade);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Localidade localidade){
        localidadeService.updateById(id, localidade);
        return new ResponseEntity<>("Updated localidade id: " + id, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable Long id){
        localidadeService.deleteById(id);
        return new ResponseEntity<>("Deleted localidade id: " + id,HttpStatus.OK);
    }


}
