package com.petland.controller;

import com.petland.model.ProdutoServico;
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
    public List<ProdutoServico> listar() {
        return produtoServicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoServico> buscarPorId(@PathVariable Integer id) {
        return produtoServicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoServico> criar(@RequestBody ProdutoServico produtoServico) {
        ProdutoServico salvo = produtoServicoRepository.save(produtoServico);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoServico> atualizar(@PathVariable Integer id,
                                                     @RequestBody ProdutoServico produtoServico) {
        return produtoServicoRepository.findById(id)
                .map(existente -> {
                    existente.setNome(produtoServico.getNome());
                    existente.setValor(produtoServico.getValor());
                    existente.setServico(produtoServico.getServico());
                    ProdutoServico atualizado = produtoServicoRepository.save(existente);
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
