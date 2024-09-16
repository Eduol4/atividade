package servicesTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.avaliativa.avaliativa.entities.EventoEntity;
import com.avaliativa.avaliativa.entities.IdeiaEntity;
import com.avaliativa.avaliativa.entities.UsuarioEntity;
import com.avaliativa.avaliativa.repositories.EventoRepository;
import com.avaliativa.avaliativa.repositories.IdeiaRepository;
import com.avaliativa.avaliativa.repositories.UsuarioRepository;

public class IdeiaServiceTest {

    @InjectMocks
    private IdeiaService ideiaService;

    @Mock
    private IdeiaRepository ideiaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EventoRepository eventoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Testes para a função postarIdeia
    @Test
    void testPostarIdeiaComSucesso() throws Exception {
        // Criação dos objetos de teste
        IdeiaEntity ideia = new IdeiaEntity();
        UsuarioEntity colaborador = new UsuarioEntity();
        colaborador.setPerfil("COLABORADOR");
        EventoEntity evento = new EventoEntity();

        // Mock do comportamento do repositório
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(colaborador));
        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));
        when(ideiaRepository.save(ideia)).thenReturn(ideia);

        // Testa a função de postagem de ideias
        IdeiaEntity resultado = ideiaService.postarIdeia(ideia, 1L, 1L);
        assertNotNull(resultado);
        verify(ideiaRepository, times(1)).save(ideia);
    }

    @Test
    void testPostarIdeiaSemPermissao() {
        // Dados de teste
        IdeiaEntity ideia = new IdeiaEntity();
        UsuarioEntity colaborador = new UsuarioEntity();
        colaborador.setPerfil("USUARIO");

        // Mock do comportamento
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(colaborador));

        // Testa se uma exceção é lançada quando o colaborador não tem permissão
        Exception exception = assertThrows(Exception.class, () -> {
            ideiaService.postarIdeia(ideia, 1L, 1L);
        });

        assertEquals("Usuário não tem permissão para postar ideias", exception.getMessage());
        verify(ideiaRepository, never()).save(any(IdeiaEntity.class));
    }

    // Testes para a função avaliarIdeia
    @Test
    void testAvaliarIdeiaComSucesso() throws Exception {
        IdeiaEntity ideia = new IdeiaEntity();
        UsuarioEntity jurado = new UsuarioEntity();
        jurado.setIdUsuario(1L);
        ideia.setAvaliadores(Arrays.asList(jurado));

        when(ideiaRepository.findById(1L)).thenReturn(Optional.of(ideia));
        ideiaService.avaliarIdeia(1L, 1L, 8.0);
        assertEquals(1, ideia.getNotas().size());
        assertEquals(8.0, ideia.getNotas().get(0));
    }

    @Test
    void testAvaliarIdeiaComNotaInvalida() {
        Exception exception = assertThrows(Exception.class, () -> {
            ideiaService.avaliarIdeia(1L, 1L, 2.0);
        });
        assertEquals("A nota deve ser entre 3 e 10", exception.getMessage());
    }

    // Testes para a função calcularMediaNotas
    @Test
    void testCalcularMediaNotasComSucesso() throws Exception {
        IdeiaEntity ideia = new IdeiaEntity();
        ideia.setNotas(Arrays.asList(8.0, 9.0));

        when(ideiaRepository.findById(1L)).thenReturn(Optional.of(ideia));

        Double media = ideiaService.calcularMediaNotas(1L);
        assertEquals(8.5, media);
    }

    @Test
    void testCalcularMediaNotasSemNotasSuficientes() {
        IdeiaEntity ideia = new IdeiaEntity();
        ideia.setNotas(Arrays.asList(8.0));

        when(ideiaRepository.findById(1L)).thenReturn(Optional.of(ideia));

        Exception exception = assertThrows(Exception.class, () -> {
            ideiaService.calcularMediaNotas(1L);
        });
        assertEquals("A ideia deve ser avaliada por 2 jurados", exception.getMessage());
    }
}
