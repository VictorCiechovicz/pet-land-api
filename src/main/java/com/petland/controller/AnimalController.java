package com.petland.controller;

import com.petland.model.entity.AnimalEntity;
import com.petland.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<AnimalEntity> listar() {
        return animalService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalEntity> buscarPorId(@PathVariable Integer id) {
        return animalService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AnimalEntity> criar(@RequestBody AnimalEntity animal) {
        AnimalEntity salvo = animalService.criar(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalEntity> atualizar(@PathVariable Integer id,
                                                   @RequestBody AnimalEntity animal) {
        return animalService.atualizar(id, animal)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (animalService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
