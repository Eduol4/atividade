package com.atividade1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atividade1.entities.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    List<ClienteEntity> findByNomeContaining(String nome);
    List<ClienteEntity> findByIdadeBetween(Integer idadeMin, Integer idadeMax);
}
