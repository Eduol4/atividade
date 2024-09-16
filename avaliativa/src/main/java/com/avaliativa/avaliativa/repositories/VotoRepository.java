package com.avaliativa.avaliativa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.avaliativa.avaliativa.entities.VotoEntity;
import com.avaliativa.avaliativa.entities.IdeiaEntity;
import com.avaliativa.avaliativa.entities.UsuarioEntity;

@Repository
public interface VotoRepository extends JpaRepository<VotoEntity, Long> {
    boolean UsuarioIdeia(UsuarioEntity usuarioEntity, IdeiaEntity ideiaEntity);
    List<VotoEntity> findByIdeia(IdeiaEntity ideiaEntity);
    
    @Query("SELECT v.ideia, COUNT(v) as votos FROM VotoEntity v GROUP BY v.ideia ORDER BY votos DESC")
    List<IdeiaEntity> findByTopVotos();
}
