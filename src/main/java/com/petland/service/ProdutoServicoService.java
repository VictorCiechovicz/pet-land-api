package com.petland.service;

import com.petland.model.entity.ProdutoServicoEntity;
import com.petland.repository.ProdutoServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServicoService {

    private final ProdutoServicoRepository produtoServicoRepository;

    public ProdutoServicoService(ProdutoServicoRepository produtoServicoRepository) {
        this.produtoServicoRepository = produtoServicoRepository;
    }

    public List<ProdutoServicoEntity> listarTodos() {
        return produtoServicoRepository.findAll();
    }

    public Optional<ProdutoServicoEntity> buscarPorId(Integer id) {
        return produtoServicoRepository.findById(id);
    }

    public ProdutoServicoEntity criar(ProdutoServicoEntity produtoServico) {
        return produtoServicoRepository.save(produtoServico);
    }

    public Optional<ProdutoServicoEntity> atualizar(Integer id, ProdutoServicoEntity produtoServico) {
        return produtoServicoRepository.findById(id)
                .map(existente -> {
                    existente.setNome(produtoServico.getNome());
                    existente.setValor(produtoServico.getValor());
                    existente.setServico(produtoServico.getServico());
                    return produtoServicoRepository.save(existente);
                });
    }

    public boolean deletar(Integer id) {
        return produtoServicoRepository.findById(id)
                .map(produto -> {
                    produtoServicoRepository.delete(produto);
                    return true;
                })
                .orElse(false);
    }
}

