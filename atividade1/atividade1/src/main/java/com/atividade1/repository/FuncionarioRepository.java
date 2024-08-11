package com.atividade1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atividade1.entities.FuncionarioEntity;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {
    List<FuncionarioEntity> findByNomeContaining(String nome);
}
