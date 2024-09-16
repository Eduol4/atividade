package servicesTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.avaliativa.avaliativa.entities.EventoEntity;
import com.avaliativa.avaliativa.entities.UsuarioEntity;
import com.avaliativa.avaliativa.repositories.EventoRepository;
import com.avaliativa.avaliativa.repositories.UsuarioRepository;

@SpringBootTest
public class EventoServiceTest {

    @InjectMocks
    private EventoService eventoService;

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarEventoComSucesso() throws Exception {
        // Dados de teste
        EventoEntity evento = new EventoEntity();
        UsuarioEntity admin = new UsuarioEntity();
        admin.setPerfil("ADMIN");

        // Mock do comportamento do repositório
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(admin));
        when(eventoRepository.save(evento)).thenReturn(evento);

        // Testa o método de criação do evento
        EventoEntity resultado = eventoService.criarEvento(evento, 1L);
        assertNotNull(resultado);
        verify(eventoRepository, times(1)).save(evento);
    }

    @Test
    void testCriarEventoSemPermissao() {
        // Dados de teste
        EventoEntity evento = new EventoEntity();
        UsuarioEntity user = new UsuarioEntity();
        user.setPerfil("USER");

        // Mock do comportamento do repositório
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(user));

        // Testa se a exceção é lançada ao tentar criar um evento sem permissão
        Exception exception = assertThrows(Exception.class, () -> {
            eventoService.criarEvento(evento, 1L);
        });

        assertEquals("Usuário sem permissão para criar evento", exception.getMessage());
        verify(eventoRepository, never()).save(any(EventoEntity.class));
    }
}
