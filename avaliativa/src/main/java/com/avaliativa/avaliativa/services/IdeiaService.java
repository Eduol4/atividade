package com.avaliativa.avaliativa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaliativa.avaliativa.entities.IdeiaEntity;
import com.avaliativa.avaliativa.entities.EventoEntity;
import com.avaliativa.avaliativa.entities.UsuarioEntity;
import com.avaliativa.avaliativa.repositories.EventoRepository;
import com.avaliativa.avaliativa.repositories.IdeiaRepository;
import com.avaliativa.avaliativa.repositories.UsuarioRepository;

@Service
public class IdeiaService {

    @Autowired
    private IdeiaRepository ideiaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public IdeiaEntity postarIdeia(IdeiaEntity ideiaEntity, Long idColaborador, Long idEvento) throws Exception {
        UsuarioEntity colaborador = usuarioRepository.findById(idColaborador)
                .orElseThrow(() -> new Exception("Colaborador não encontrado"));

        if (!colaborador.getPerfil().equals("COLABORADOR")) {
            throw new Exception("Usuário não tem permissão para postar ideias");
        }

        EventoEntity eventoEntity = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new Exception("Evento não encontrado"));

        // Verificar se o colaborador já está vinculado a uma ideia nesse evento
        for (IdeiaEntity i : eventoEntity.getIdeias()) {
            if (i.getColaborador().equals(colaborador)) {
                throw new Exception("Colaborador já possui uma ideia neste evento");
            }
        }

        ideiaEntity.setColaborador(colaborador);
        ideiaEntity.setEvento(eventoEntity);
        return ideiaRepository.save(ideiaEntity);
    }
    
    public void avaliarIdeia(Long idJurado, Long idIdeia, Double nota) throws Exception {
        if (nota < 3 || nota > 10) {
            throw new Exception("A nota deve ser entre 3 e 10");
        }

        IdeiaEntity ideia = ideiaRepository.findById(idIdeia)
                .orElseThrow(() -> new Exception("Ideia não encontrada"));

        // Verifica se o jurado está autorizado a avaliar
        boolean autorizado = ideia.getAvaliadores().stream()
                .anyMatch(jurado -> jurado.getIdUsuario().equals(idJurado));

        if (!autorizado) {
            throw new Exception("Jurado não autorizado a avaliar esta ideia");
        }

        if (ideia.getNotas().size() >= 2) {
            throw new Exception("A ideia já foi avaliada por 2 jurados");
        }

        ideia.getNotas().add(nota);
        ideiaRepository.save(ideia);
    }
    
    public Double calcularMediaNotas(Long idIdeia) throws Exception {
        IdeiaEntity ideia = ideiaRepository.findById(idIdeia)
                .orElseThrow(() -> new Exception("Ideia não encontrada"));

        List<Double> notas = ideia.getNotas();

        if (notas.size() != 2) {
            throw new Exception("A ideia deve ser avaliada por 2 jurados");
        }

        // Calcula a média das notas
        double media = (notas.get(0) + notas.get(1)) / 2;
        ideia.setMediaNota(media);

        ideiaRepository.save(ideia);
        
        return media;
    }
}
