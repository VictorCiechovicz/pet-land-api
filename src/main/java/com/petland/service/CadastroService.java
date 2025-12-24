package com.petland.service;

import com.petland.model.entity.CadastroEntity;
import com.petland.repository.CadastroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroService {

    private final CadastroRepository cadastroRepository;

    public CadastroService(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    public List<CadastroEntity> listarTodos() {
        return cadastroRepository.findAll();
    }

    public Optional<CadastroEntity> buscarPorId(Integer id) {
        return cadastroRepository.findById(id);
    }

    public CadastroEntity criar(CadastroEntity cadastro) {
        return cadastroRepository.save(cadastro);
    }

    public Optional<CadastroEntity> atualizar(Integer id, CadastroEntity cadastro) {
        return cadastroRepository.findById(id)
                .map(existente -> {
                    existente.setNome(cadastro.getNome());
                    existente.setPerfilDTO(cadastro.getPerfilDTO());
                    existente.setEnderecoDTO(cadastro.getEnderecoDTO());
                    return cadastroRepository.save(existente);
                });
    }

    public boolean deletar(Integer id) {
        return cadastroRepository.findById(id)
                .map(cadastro -> {
                    cadastroRepository.delete(cadastro);
                    return true;
                })
                .orElse(false);
    }
}
