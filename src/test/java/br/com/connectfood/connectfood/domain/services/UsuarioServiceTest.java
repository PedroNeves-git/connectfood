package br.com.connectfood.connectfood.domain.services;

import br.com.connectfood.connectfood.application.dto.UsuarioRequestDTO;
import br.com.connectfood.connectfood.application.dto.login.LoginRequestDTO;
import br.com.connectfood.connectfood.application.dto.login.LoginResponseDTO;
import br.com.connectfood.connectfood.domain.models.TipoUsuario;
import br.com.connectfood.connectfood.domain.models.Usuario;
import br.com.connectfood.connectfood.domain.repositories.TipoUsuarioRepository;
import br.com.connectfood.connectfood.domain.repositories.UsuarioRepository;
import br.com.connectfood.connectfood.domain.services.exceptions.ConflictException;
import br.com.connectfood.connectfood.domain.services.exceptions.ResourceNotFoundException;
import br.com.connectfood.connectfood.domain.services.exceptions.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void testFindAllUsuarios() {
        int page = 1;
        int size = 2;

        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        usuario1.setNome("Pedro");

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNome("Maria");

        List<Usuario> usuariosMockados = List.of(usuario1, usuario2);
        Page<Usuario> pageResult = new PageImpl<>(usuariosMockados);

        when(usuarioRepository.findAll(any(Pageable.class))).thenReturn(pageResult);

        List<Usuario> resultado = usuarioService.findAllUsuarios(page, size);

        assertEquals(2, resultado.size());
        assertEquals("Pedro", resultado.get(0).getNome());
        assertEquals("Maria", resultado.get(1).getNome());
    }

    @Test
    void testFindUsuarioById() {
        Long id = 1L;
        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(id);
        usuarioMock.setNome("Pedro");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioMock));

        Usuario result = usuarioService.findUsuarioById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Pedro", result.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoForEncontrado() {
        Long id = 999L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.findUsuarioById(id));
    }

    @Test
    void testSalvarUsuarioComSucesso() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(1L,
                "Pedro",
                "pedro@gmail.com",
                "1234",
                OffsetDateTime.now(),
                "Rua X",
                "pedro123",
                1L);

        TipoUsuario tipo = new TipoUsuario(1L, "Dono");

        when(usuarioRepository.findByLogin("pedro123")).thenReturn(Optional.empty());
        when(tipoUsuarioRepository.findById(1L)).thenReturn(Optional.of(tipo));
        when(usuarioRepository.save(any())).thenAnswer(inv -> {
            Usuario u = inv.getArgument(0);
            u.setId(1L);
            return u;
        });

        assertDoesNotThrow(() -> usuarioService.saveUsuario(dto));
    }

    @Test
    void testSalvarUsuarioComLoginRepetido() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                1L,
                "Pedro",
                "pedro@gmail.com",
                "1234",
                OffsetDateTime.now(),
                "Rua X",
                "pedro123",
                1L
        );

        when(usuarioRepository.findByLogin("pedro123")).thenReturn(Optional.of(new Usuario()));

        assertThrows(ConflictException.class, () -> usuarioService.saveUsuario(dto));
    }

    @Test
    void testAtualizarUsuarioComSucesso() {
        Usuario existente = new Usuario();
        existente.setId(1L);

        Usuario atualizado = new Usuario();
        atualizado.setNome("Novo Nome");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(usuarioRepository.save(any())).thenReturn(existente);

        assertDoesNotThrow(() -> usuarioService.updateUsuario(atualizado, 1L));
    }

    @Test
    void testAtualizarUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Usuario usuario = new Usuario();
        usuario.setNome("Teste");

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.updateUsuario(usuario, 1L));
    }

    @Test
    void testDeleteUsuarioComSucesso() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        doNothing().when(usuarioRepository).delete(usuario);

        assertDoesNotThrow(() -> usuarioService.deleteUsuario(1L));
    }

    @Test
    void testDeleteUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.deleteUsuario(1L));
    }

    @Test
    void testTrocarSenhaComSucesso() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setSenha("1234");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any())).thenReturn(usuario);

        assertDoesNotThrow(() -> usuarioService.trocarSenha(1L, "1234", "novaSenha"));
    }

    @Test
    void testTrocarSenhaIncorreta() {
        Usuario usuario = new Usuario();
        usuario.setSenha("1234");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        assertThrows(UnauthorizedException.class, () -> usuarioService.trocarSenha(1L, "errada", "novaSenha"));
    }

    @Test
    void testTrocarSenhaMesmaSenha() {
        Usuario usuario = new Usuario();
        usuario.setSenha("1234");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        assertThrows(UnauthorizedException.class, () -> usuarioService.trocarSenha(1L, "1234", "1234"));
    }

    @Test
    void testAutenticarComSucesso() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Pedro");
        usuario.setSenha("1234");
        usuario.setEmail("pedro@gmail.com");

        when(usuarioRepository.findByLogin("pedro123")).thenReturn(Optional.of(usuario));

        LoginRequestDTO login = new LoginRequestDTO("pedro123", "1234");

        LoginResponseDTO response = usuarioService.autenticar(login);

        assertEquals("Pedro", response.login());
        assertEquals("*****", response.senha());
        assertEquals("pedro@gmail.com", response.email());
    }

    @Test
    void testAutenticarLoginNaoEncontrado() {
        when(usuarioRepository.findByLogin("naoexiste")).thenReturn(Optional.empty());

        LoginRequestDTO login = new LoginRequestDTO("naoexiste", "1234");

        assertThrows(ResourceNotFoundException.class, () -> usuarioService.autenticar(login));
    }

    @Test
    void testAutenticarSenhaErrada() {
        Usuario usuario = new Usuario();
        usuario.setSenha("1234");

        when(usuarioRepository.findByLogin("pedro123")).thenReturn(Optional.of(usuario));

        LoginRequestDTO login = new LoginRequestDTO("pedro123", "errada");

        assertThrows(UnauthorizedException.class, () -> usuarioService.autenticar(login));
    }
}
