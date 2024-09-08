package com.example.atividade2.atividade2.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.atividade2.atividade2.services.ListasService;

@RestController
@RequestMapping("/listas")
public class ListasController {
    
    @Autowired
    private ListasService listasService;

    @PostMapping("/somar")
    public ResponseEntity<Integer> soma(@RequestBody List<Integer> numerosSoma) {
        int resultadoSoma = listasService.somar(numerosSoma);
        return ResponseEntity.ok(resultadoSoma);
    }

    @PostMapping("/media")
    public ResponseEntity<Double> media(@RequestBody List<Integer> numerosMedia) {
        double resultadoMedia = listasService.calcularMedia(numerosMedia);
        return ResponseEntity.ok(resultadoMedia);
    }

    @PostMapping("/comparar")
    public ResponseEntity<Map<String, Integer>> maiorMenor(@RequestBody List<Integer> numerosEncontrar) {
        Map<String, Integer> resultadoMaiorMenor = listasService.encontrarMaiorMenor(numerosEncontrar);
        return ResponseEntity.ok(resultadoMaiorMenor);
    }
}
