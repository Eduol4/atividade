package com.avaliativa.avaliativa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avaliativa.avaliativa.entities.UsuarioEntity;
import com.avaliativa.avaliativa.entities.EventoEntity;
import com.avaliativa.avaliativa.services.EventoService;
import com.avaliativa.avaliativa.services.UsuarioService;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criar/{idAdmin}")
    public ResponseEntity<EventoEntity> criar(@PathVariable Long idAdmin, @RequestBody EventoEntity eventoEntity) {
        try {
            UsuarioEntity admin = usuarioService.findById(idAdmin);
            if (!admin.getPerfil().equals("ADMIN")) {
                throw new Exception("Usuário sem permissão para criar evento");
            }
            return ResponseEntity.ok(eventoService.criarEvento(eventoEntity, idAdmin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @PutMapping("/selecionarJurados/{eventoId}/{idAdmin}")
    public ResponseEntity<String> selecionar(@PathVariable Long idEvento, @PathVariable Long idAdmin, @RequestBody List<Long> idJurados) {
        try {
            UsuarioEntity admin = usuarioService.findById(idAdmin);
            if (!admin.getPerfil().equals("ADMIN")) {
                throw new Exception("Usuário sem permissão para selecionar jurados");
            }
            eventoService.selecionarJurados(idEvento, idJurados, idAdmin);
            return ResponseEntity.ok("Jurados selecionados com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

}
