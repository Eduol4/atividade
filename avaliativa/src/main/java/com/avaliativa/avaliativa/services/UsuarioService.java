package com.avaliativa.avaliativa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaliativa.avaliativa.entities.UsuarioEntity;
import com.avaliativa.avaliativa.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioEntity registrarUsuario(UsuarioEntity usuarioEntity) {
	    return usuarioRepository.save(usuarioEntity);
	}

    public List<UsuarioEntity> listAllUsuario() {
        return usuarioRepository.findAll();
    }
    
    public UsuarioEntity findById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).get();
    }

    public void alterarPerfil(Long idUsuario, String novoPerfil) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioEntity.setPerfil(novoPerfil);
        usuarioRepository.save(usuarioEntity);
    }
}
