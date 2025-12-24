package com.petland.controller;

import com.petland.model.entity.CadastroEntity;
import com.petland.service.CadastroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {

    private final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    @GetMapping
    public List<CadastroEntity> listar() {
        return cadastroService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadastroEntity> buscarPorId(@PathVariable Integer id) {
        return cadastroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CadastroEntity> criar(@RequestBody CadastroEntity cadastro) {
        CadastroEntity salvo = cadastroService.criar(cadastro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CadastroEntity> atualizar(@PathVariable Integer id,
                                                     @RequestBody CadastroEntity cadastro) {
        return cadastroService.atualizar(id, cadastro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (cadastroService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
