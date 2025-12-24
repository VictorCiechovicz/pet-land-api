package com.petland.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petland.model.entity.ProdutoServicoEntity;

public interface ProdutoServicoRepository extends JpaRepository<ProdutoServicoEntity, Integer>{

}