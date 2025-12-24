package com.petland.repository;

import com.petland.model.AtendimentoStatus;
import com.petland.model.entity.AtendimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AtendimentoRepository extends JpaRepository<AtendimentoEntity, Integer> {

    List<AtendimentoEntity> findByStatus(AtendimentoStatus status);

    List<AtendimentoEntity> findByClienteId(Integer clienteId);

    List<AtendimentoEntity> findByAnimalId(Integer animalId);

    List<AtendimentoEntity> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
}

