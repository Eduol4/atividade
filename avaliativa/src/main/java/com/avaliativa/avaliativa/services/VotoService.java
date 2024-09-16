package com.avaliativa.avaliativa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaliativa.avaliativa.entities.IdeiaEntity;
import com.avaliativa.avaliativa.entities.VotoEntity;
import com.avaliativa.avaliativa.entities.UsuarioEntity;
import com.avaliativa.avaliativa.repositories.IdeiaRepository;
import com.avaliativa.avaliativa.repositories.UsuarioRepository;
import com.avaliativa.avaliativa.repositories.VotoRepository;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IdeiaRepository ideiaRepository;

    // Registrar um voto
    public String votar(Long idUsuario, Long idIdeia) throws Exception {
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));
        IdeiaEntity ideia = ideiaRepository.findById(idIdeia)
                .orElseThrow(() -> new Exception("Ideia não encontrada"));

        // Verificar se o usuário já votou nesta ideia
        if (votoRepository.UsuarioIdeia(usuario, ideia)) {
            throw new Exception("Usuário já votou nesta ideia");
        }

        VotoEntity votoEntity = new VotoEntity();
        votoEntity.setUsuario(usuario);
        votoEntity.setIdeia(ideia);
        votoRepository.save(votoEntity);

        return "Voto registrado com sucesso!";
    }

    public List<IdeiaEntity> listarTopIdeias() {
        return votoRepository.findByTopVotos();
    }
}
