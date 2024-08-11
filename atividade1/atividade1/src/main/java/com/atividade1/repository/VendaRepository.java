package com.atividade1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atividade1.entities.VendaEntity;

@Repository
public interface VendaRepository extends JpaRepository<VendaEntity, Long> {
    List<VendaEntity> findTop10ByOrderByValorTotalDesc();
    List<VendaEntity> findByCliente_NomeContaining(String nome);
    List<VendaEntity> findByFuncionario_NomeContaining(String nome);
}
