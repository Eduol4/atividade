package com.example.atividade2.atividade2.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ListasService {
    // public float somar(List<Float> numerosSoma) {
    //     return numerosSoma.stream().mapToFloat(Float::floatValue()).sum();
    // }

    // Tentei fazer com "Float", mas tinha erro no parênteses do "mapToFloat"

    public int somar(List<Integer> numerosSoma) {
        return numerosSoma.stream().mapToInt(Integer::intValue).sum();
    }

    public double calcularMedia(List<Integer> numerosMedia) {
        return numerosMedia.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    public Map<String, Integer> encontrarMaiorMenor(List<Integer> numerosEncontrar) {
        int maior = Collections.max(numerosEncontrar);
        int menor = Collections.min(numerosEncontrar);
        Map<String, Integer> resultado = new HashMap<>();
        resultado.put("maior", maior);
        resultado.put("menor", menor);
        return resultado;
    }

    // Bati bastante a cabeça nessa de encontrar o maior e o menor número, precisei da ajuda do GPT
}
