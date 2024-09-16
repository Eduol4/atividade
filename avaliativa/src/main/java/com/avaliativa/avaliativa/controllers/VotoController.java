package com.avaliativa.avaliativa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avaliativa.avaliativa.services.VotoService;
import com.avaliativa.avaliativa.entities.IdeiaEntity;

@RestController
@RequestMapping("/votos")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping("/votar")
    public ResponseEntity<String> votar(@RequestParam Long idUsuario, @RequestParam Long idIdeia) {
        try {
            String resposta = votoService.votar(idUsuario, idIdeia);
            return ResponseEntity.ok(resposta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/top10")
    public ResponseEntity<List<IdeiaEntity>> listarTop10Ideias() {
        return ResponseEntity.ok(votoService.listarTopIdeias());
    }
}
