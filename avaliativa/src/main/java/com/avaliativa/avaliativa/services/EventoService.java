package com.avaliativa.avaliativa.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaliativa.avaliativa.entities.EventoEntity;
import com.avaliativa.avaliativa.entities.IdeiaEntity;
import com.avaliativa.avaliativa.entities.UsuarioEntity;
import com.avaliativa.avaliativa.repositories.EventoRepository;
import com.avaliativa.avaliativa.repositories.IdeiaRepository;
import com.avaliativa.avaliativa.repositories.UsuarioRepository;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private IdeiaRepository ideiaRepository;

    public EventoEntity criarEvento(EventoEntity eventoEntity, Long idAdmin) throws Exception {
        UsuarioEntity admin = usuarioRepository.findById(idAdmin)
                .orElseThrow(() -> new Exception("Admin não encontrado"));

        if (!admin.getPerfil().equals("ADMIN")) {
            throw new Exception("Usuário sem permissão para criar evento");
        }

        return eventoRepository.save(eventoEntity);
    }

    public void selecionarJurados(Long idEvento, List<Long> idJurados, Long idAdmin) throws Exception {
        EventoEntity eventoEntity = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new Exception("Evento não encontrado"));

        UsuarioEntity admin = usuarioRepository.findById(idAdmin)
                .orElseThrow(() -> new Exception("Admin não encontrado"));

        if (!admin.getPerfil().equals("ADMIN")) {
            throw new Exception("Usuário sem permissão para selecionar jurados");
        }

        List<UsuarioEntity> jurados = usuarioRepository.findAllById(idJurados);
        eventoEntity.setJurados(jurados);
        eventoRepository.save(eventoEntity);
    }
    
    public void distribuirIdeias(Long idEvento) throws Exception {
        EventoEntity eventoEntity = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new Exception("Evento não encontrado"));

        List<IdeiaEntity> ideias = ideiaRepository.findByEvento(eventoEntity);
        List<UsuarioEntity> jurados = eventoEntity.getJurados();

        if (jurados.isEmpty()) {
            throw new Exception("Nenhum jurado atribuído ao evento");
        }

        // Distribuição igualitária e aleatória
        int juradoIndex = 0;
        Collections.shuffle(ideias);  // Embaralha a lista de ideias

        for (IdeiaEntity ideia : ideias) {
            // Atribui jurados de forma circular às ideias
            UsuarioEntity jurado = jurados.get(juradoIndex % jurados.size());
            ideia.getAvaliadores().add(jurado);
            juradoIndex++;

            // Garantindo que cada ideia tenha 2 jurados
            UsuarioEntity segundoJurado = jurados.get((juradoIndex) % jurados.size());
            ideia.getAvaliadores().add(segundoJurado);
            juradoIndex++;
        }

        ideiaRepository.saveAll(ideias);
    }
    
    public List<IdeiaEntity> listarMelhoresIdeias(Long idEvento) throws Exception {
        EventoEntity eventoEntity = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new Exception("Evento não encontrado"));

        // Busca todas as ideias do evento e as ordena pela média das notas
        List<IdeiaEntity> ideias = ideiaRepository.OrderByMedia(eventoEntity);

        return ideias.stream().limit(10).collect(Collectors.toList());
    }
}
