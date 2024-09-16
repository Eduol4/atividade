package servicesTests;

import com.avaliativa.avaliativa.entities.IdeiaEntity;
import com.avaliativa.avaliativa.entities.UsuarioEntity;
import com.avaliativa.avaliativa.entities.VotoEntity;
import com.avaliativa.avaliativa.repositories.IdeiaRepository;
import com.avaliativa.avaliativa.repositories.UsuarioRepository;
import com.avaliativa.avaliativa.repositories.VotoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VotoServiceTest {

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private IdeiaRepository ideiaRepository;

    @InjectMocks
    private VotoService votoService;

    public VotoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testVotar_Sucesso() throws Exception {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setIdUsuario(1L);
        
        IdeiaEntity ideia = new IdeiaEntity();
        ideia.setIdIdeia(1L);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(ideiaRepository.findById(1L)).thenReturn(Optional.of(ideia));
        when(votoRepository.UsuarioIdeia(usuario, ideia)).thenReturn(false);
        
        String resultado = votoService.votar(1L, 1L);
        
        assertEquals("Voto registrado com sucesso!", resultado);
        verify(votoRepository, times(1)).save(any(VotoEntity.class));
    }

    @Test
    public void testVotar_UsuarioNaoEncontrado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(Exception.class, () -> {
            votoService.votar(1L, 1L);
        });
        
        assertEquals("Usuário não encontrado", exception.getMessage());
    }

    @Test
    public void testListarTopIdeias_Sucesso() {
        List<IdeiaEntity> ideias = List.of(new IdeiaEntity(), new IdeiaEntity());
        
        when(votoRepository.findByTopVotos()).thenReturn(ideias);
        
        List<IdeiaEntity> resultado = votoService.listarTopIdeias();
        
        assertEquals(2, resultado.size());
        verify(votoRepository, times(1)).findByTopVotos();
    }

    @Test
    public void testListarTopIdeias_Vazio() {
        List<IdeiaEntity> ideias = List.of();
        
        when(votoRepository.findByTopVotos()).thenReturn(ideias);
        
        List<IdeiaEntity> resultado = votoService.listarTopIdeias();
        
        assertTrue(resultado.isEmpty());
        verify(votoRepository, times(1)).findByTopVotos();
    }
}
