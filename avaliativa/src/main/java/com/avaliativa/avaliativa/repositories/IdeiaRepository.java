package com.avaliativa.avaliativa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avaliativa.avaliativa.entities.EventoEntity;
import com.avaliativa.avaliativa.entities.IdeiaEntity;

@Repository
public interface IdeiaRepository extends JpaRepository<IdeiaEntity, Long> {
    List<IdeiaEntity> findByEvento(EventoEntity eventoEntity);
    List<IdeiaEntity> OrderByMedia(EventoEntity eventoEntity);
    
}
