package com.petland.controller;

import com.petland.model.AtendimentoStatus;
import com.petland.model.entity.AtendimentoEntity;
import com.petland.service.AtendimentoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @GetMapping
    public List<AtendimentoEntity> listar() {
        return atendimentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoEntity> buscarPorId(@PathVariable Integer id) {
        return atendimentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public List<AtendimentoEntity> buscarPorStatus(@PathVariable AtendimentoStatus status) {
        return atendimentoService.buscarPorStatus(status);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<AtendimentoEntity> buscarPorCliente(@PathVariable Integer clienteId) {
        return atendimentoService.buscarPorCliente(clienteId);
    }

    @GetMapping("/animal/{animalId}")
    public List<AtendimentoEntity> buscarPorAnimal(@PathVariable Integer animalId) {
        return atendimentoService.buscarPorAnimal(animalId);
    }

    @GetMapping("/periodo")
    public List<AtendimentoEntity> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return atendimentoService.buscarPorPeriodo(inicio, fim);
    }

    @PostMapping
    public ResponseEntity<AtendimentoEntity> criar(@RequestBody AtendimentoEntity atendimento) {
        AtendimentoEntity salvo = atendimentoService.criar(atendimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoEntity> atualizar(@PathVariable Integer id,
                                                        @RequestBody AtendimentoEntity atendimento) {
        return atendimentoService.atualizar(id, atendimento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AtendimentoEntity> atualizarStatus(@PathVariable Integer id,
                                                              @RequestParam AtendimentoStatus status) {
        return atendimentoService.atualizarStatus(id, status)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (atendimentoService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

