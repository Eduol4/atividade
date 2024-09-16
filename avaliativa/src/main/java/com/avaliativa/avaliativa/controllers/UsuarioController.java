package com.avaliativa.avaliativa.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avaliativa.avaliativa.entities.UsuarioEntity;
import com.avaliativa.avaliativa.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/registrar")
	public ResponseEntity<UsuarioEntity> registrar(@Validated @RequestBody UsuarioEntity usuarioEntity) {
		try {
			return ResponseEntity.ok(usuarioService.registrarUsuario(usuarioEntity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
    
    @GetMapping("/listar")
	public ResponseEntity<List<UsuarioEntity>> listAll() {
		try {
			return ResponseEntity.ok(usuarioService.listAllUsuario());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

    @PutMapping("/alterarPerfil/{idAdmin}/{idUsuario}")
    public ResponseEntity<String> alterarPerfil(@PathVariable Long idAdmin, @PathVariable Long idUsuario, @RequestParam String perfil) {
        try {
            // Buscar o usuário admin que está tentando alterar o perfil
            UsuarioEntity admin = usuarioService.findById(idAdmin);

            // Verificar se o usuário é admin
            if (!admin.getPerfil().equals("ADMIN")) {
                return new ResponseEntity<>("Permissão negada: Somente administradores podem alterar perfis", HttpStatus.FORBIDDEN);
            }

            usuarioService.alterarPerfil(idUsuario, perfil);

            return new ResponseEntity<>("Perfil alterado com sucesso", HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao alterar o perfil", HttpStatus.BAD_REQUEST);
        }
    }
}
