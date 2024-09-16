package com.avaliativa.avaliativa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avaliativa.avaliativa.entities.EventoEntity;

@Repository
public interface EventoRepository extends JpaRepository<EventoEntity, Long> {
    
}
