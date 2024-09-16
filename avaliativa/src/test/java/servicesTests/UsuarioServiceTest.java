package servicesTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.avaliativa.avaliativa.entities.UsuarioEntity;
import com.avaliativa.avaliativa.repositories.UsuarioRepository;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Testes para a função registrarUsuario
    @Test
    void testRegistrarUsuarioComSucesso() {
        // Criando o usuário de teste
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome("João");

        // Mock do comportamento do repositório
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Testando o registro do usuário
        UsuarioEntity resultado = usuarioService.registrarUsuario(usuario);
        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    // Teste para listar todos os usuários
    @Test
    void testListAllUsuarioComSucesso() {
        // Mockando a resposta do repositório
        UsuarioEntity usuario1 = new UsuarioEntity();
        usuario1.setNome("Maria");
        UsuarioEntity usuario2 = new UsuarioEntity();
        usuario2.setNome("Pedro");

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        // Testando o serviço de listar usuários
        List<UsuarioEntity> resultado = usuarioService.listAllUsuario();
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Maria", resultado.get(0).getNome());
        assertEquals("Pedro", resultado.get(1).getNome());
    }

    // Testes para a função findById
    @Test
    void testFindByIdComSucesso() {
        // Mock do comportamento do repositório
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setIdUsuario(1L);
        usuario.setNome("Ana");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Testando a busca por ID
        UsuarioEntity resultado = usuarioService.findById(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdUsuario());
        assertEquals("Ana", resultado.getNome());
    }

    @Test
    void testFindByIdUsuarioNaoEncontrado() {
        // Mockando o caso onde o usuário não é encontrado
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        // Testando a busca de um ID inexistente
        Exception exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.findById(1L);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
    }

    // Testes para a função alterarPerfil
    @Test
    void testAlterarPerfilComSucesso() {
        // Mockando o comportamento
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setIdUsuario(1L);
        usuario.setPerfil("COLABORADOR");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Testando a alteração de perfil
        usuarioService.alterarPerfil(1L, "ADMIN");
        assertEquals("ADMIN", usuario.getPerfil());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testAlterarPerfilUsuarioNaoEncontrado() {
        // Mockando o caso onde o usuário não é encontrado
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        // Testando a alteração de perfil para um ID inexistente
        Exception exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.alterarPerfil(1L, "ADMIN");
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(usuarioRepository, never()).save(any(UsuarioEntity.class));
    }
}
