package com.example.atividade2.atividade2.servicesTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.atividade2.atividade2.services.ListasService;

@SpringBootTest
public class ListasServiceTest {
    
    @Autowired
    ListasService listasService;

    @Test
    public void testeSomar() {
        List<Integer> numerosSoma = Arrays.asList(5, 10, 15);
        int resultadoSomar = listasService.somar(numerosSoma);
        assertEquals(30, resultadoSomar);

        numerosSoma = Arrays.asList(5, 15, 25);
        resultadoSomar = listasService.somar(numerosSoma);
        assertEquals(45, resultadoSomar);
    }

    @Test
    public void testeMedia() {
        List<Integer> numerosMedia = Arrays.asList(10, 20, 30);
        double resultadoMedia = listasService.calcularMedia(numerosMedia);
        assertEquals(20.0, resultadoMedia);

        numerosMedia = Arrays.asList(5, 15, 25);
        resultadoMedia = listasService.calcularMedia(numerosMedia);
        assertEquals(15.0, resultadoMedia);
    }

    @Test
    public void testeMaiorMenor() {
        List<Integer> numerosEncontrar = Arrays.asList(10, 20, 30);
        Map<String, Integer> resultado = listasService.encontrarMaiorMenor(numerosEncontrar);
        assertEquals(30, resultado.get("maior"));
        assertEquals(10, resultado.get("menor"));

        numerosEncontrar = Arrays.asList(5, 15, 25);
        resultado = listasService.encontrarMaiorMenor(numerosEncontrar);
        assertEquals(25, resultado.get("maior"));
        assertEquals(5, resultado.get("menor"));
    }
}
