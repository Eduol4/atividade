package com.avaliativa.avaliativa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avaliativa.avaliativa.entities.IdeiaEntity;
import com.avaliativa.avaliativa.services.IdeiaService;
import com.avaliativa.avaliativa.services.EventoService;

@RestController
@RequestMapping("/ideias")
public class IdeiaController {

    @Autowired
    private IdeiaService ideiaService;
    
    @Autowired
    private EventoService eventoService;

    @PostMapping("/postar/{colaboradorId}/{eventoId}")
    public ResponseEntity<IdeiaEntity> postar(@PathVariable Long idColaborador, @PathVariable Long idEvento, @RequestBody IdeiaEntity ideiaEntity) {
        try {
            return ResponseEntity.ok(ideiaService.postarIdeia(ideiaEntity, idColaborador, idEvento));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
    
    @PostMapping("/distribuirIdeias/{eventoId}")
    public ResponseEntity<String> distribuir(@PathVariable Long idEvento) {
        try {
            eventoService.distribuirIdeias(idEvento);
            return ResponseEntity.ok("Ideias distribuídas com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao distribuir ideias.");
        }
    }
    
    @PutMapping("/avaliarIdeia/{ideiaId}")
    public ResponseEntity<String> avaliar(@PathVariable Long idIdeia, @RequestParam Long idJurado, @RequestParam Double nota) {
        try {
            ideiaService.avaliarIdeia(idIdeia, idJurado, nota);
            return ResponseEntity.ok("Ideia avaliada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao avaliar ideia.");
        }
    }
    
    @GetMapping("/media/{ideiaId}")
    public ResponseEntity<Double> calcularMedia(@PathVariable Long idIdeia) {
        try {
            Double media = ideiaService.calcularMediaNotas(idIdeia);
            return ResponseEntity.ok(media);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
