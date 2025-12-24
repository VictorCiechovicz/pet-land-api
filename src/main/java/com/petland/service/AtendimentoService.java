package com.petland.service;

import com.petland.model.AtendimentoStatus;
import com.petland.model.entity.AtendimentoEntity;
import com.petland.repository.AtendimentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;

    public AtendimentoService(AtendimentoRepository atendimentoRepository) {
        this.atendimentoRepository = atendimentoRepository;
    }

    public List<AtendimentoEntity> listarTodos() {
        return atendimentoRepository.findAll();
    }

    public Optional<AtendimentoEntity> buscarPorId(Integer id) {
        return atendimentoRepository.findById(id);
    }

    public List<AtendimentoEntity> buscarPorStatus(AtendimentoStatus status) {
        return atendimentoRepository.findByStatus(status);
    }

    public List<AtendimentoEntity> buscarPorCliente(Integer clienteId) {
        return atendimentoRepository.findByClienteId(clienteId);
    }

    public List<AtendimentoEntity> buscarPorAnimal(Integer animalId) {
        return atendimentoRepository.findByAnimalId(animalId);
    }

    public List<AtendimentoEntity> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return atendimentoRepository.findByDataHoraBetween(inicio, fim);
    }

    public AtendimentoEntity criar(AtendimentoEntity atendimento) {
        return atendimentoRepository.save(atendimento);
    }

    public Optional<AtendimentoEntity> atualizar(Integer id, AtendimentoEntity atendimento) {
        return atendimentoRepository.findById(id)
                .map(existente -> {
                    existente.setDataHora(atendimento.getDataHora());
                    existente.setDescricao(atendimento.getDescricao());
                    existente.setValor(atendimento.getValor());
                    existente.setStatus(atendimento.getStatus());
                    existente.setAnimal(atendimento.getAnimal());
                    existente.setCliente(atendimento.getCliente());
                    existente.setServico(atendimento.getServico());
                    return atendimentoRepository.save(existente);
                });
    }

    public Optional<AtendimentoEntity> atualizarStatus(Integer id, AtendimentoStatus status) {
        return atendimentoRepository.findById(id)
                .map(existente -> {
                    existente.setStatus(status);
                    return atendimentoRepository.save(existente);
                });
    }

    public boolean deletar(Integer id) {
        return atendimentoRepository.findById(id)
                .map(atendimento -> {
                    atendimentoRepository.delete(atendimento);
                    return true;
                })
                .orElse(false);
    }
}

