package com.petland.controller;

import com.petland.model.entity.ProdutoServicoEntity;
import com.petland.repository.ProdutoServicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoServicoRepository produtoServicoRepository;

    public ProdutoController(ProdutoServicoRepository produtoServicoRepository) {
        this.produtoServicoRepository = produtoServicoRepository;
    }

    @GetMapping
    public List<ProdutoServicoEntity> listar() {
        return produtoServicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoServicoEntity> buscarPorId(@PathVariable Integer id) {
        return produtoServicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoServicoEntity> criar(@RequestBody ProdutoServicoEntity produtoServicoEntity) {
        ProdutoServicoEntity salvo = produtoServicoRepository.save(produtoServicoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoServicoEntity> atualizar(@PathVariable Integer id,
                                                          @RequestBody ProdutoServicoEntity produtoServicoEntity) {
        return produtoServicoRepository.findById(id)
                .map(existente -> {
                    existente.setNome(produtoServicoEntity.getNome());
                    existente.setValor(produtoServicoEntity.getValor());
                    existente.setServico(produtoServicoEntity.getServico());
                    ProdutoServicoEntity atualizado = produtoServicoRepository.save(existente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        return produtoServicoRepository.findById(id)
                .map(produto -> {
                    produtoServicoRepository.delete(produto);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
