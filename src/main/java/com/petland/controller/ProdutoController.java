package com.petland.controller;

import com.petland.model.entity.ProdutoServicoEntity;
import com.petland.service.ProdutoServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoServicoService produtoServicoService;

    public ProdutoController(ProdutoServicoService produtoServicoService) {
        this.produtoServicoService = produtoServicoService;
    }

    @GetMapping
    public List<ProdutoServicoEntity> listar() {
        return produtoServicoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoServicoEntity> buscarPorId(@PathVariable Integer id) {
        return produtoServicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoServicoEntity> criar(@RequestBody ProdutoServicoEntity produtoServicoEntity) {
        ProdutoServicoEntity salvo = produtoServicoService.criar(produtoServicoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoServicoEntity> atualizar(@PathVariable Integer id,
                                                          @RequestBody ProdutoServicoEntity produtoServicoEntity) {
        return produtoServicoService.atualizar(id, produtoServicoEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (produtoServicoService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
