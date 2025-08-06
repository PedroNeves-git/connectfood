package br.com.connectfood.connectfood.domain.services;

import br.com.connectfood.connectfood.application.dto.RestauranteRequestDTO;
import br.com.connectfood.connectfood.domain.models.Restaurante;
import br.com.connectfood.connectfood.domain.models.TipoUsuario;
import br.com.connectfood.connectfood.domain.models.Usuario;
import br.com.connectfood.connectfood.domain.repositories.RestauranteRepository;
import br.com.connectfood.connectfood.domain.repositories.UsuarioRepository;
import br.com.connectfood.connectfood.domain.services.exceptions.ResourceNotFoundException;
import br.com.connectfood.connectfood.domain.services.exceptions.UnauthorizedException;
import br.com.connectfood.connectfood.infrastructure.mapper.RestauranteMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteServiceTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RestauranteMapper restauranteMapper;

    @InjectMocks
    private RestauranteService restauranteService;

    @Test
    void testFindAll() {
        Restaurante r1 = new Restaurante();
        r1.setId(1L);
        r1.setNome("Rest 1");

        Restaurante r2 = new Restaurante();
        r2.setId(2L);
        r2.setNome("Rest 2");

        when(restauranteRepository.findAll()).thenReturn(List.of(r1, r2));

        List<Restaurante> resultado = restauranteService.findAll();

        assertEquals(2, resultado.size());
        assertEquals("Rest 1", resultado.get(0).getNome());
        assertEquals("Rest 2", resultado.get(1).getNome());
    }

    @Test
    void testFindByIdComSucesso() {
        Restaurante r = new Restaurante();
        r.setId(1L);
        r.setNome("Mc");

        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(r));

        Restaurante resultado = restauranteService.findById(1L);

        assertEquals("Mc", resultado.getNome());
    }

    @Test
    void testFindByIdNaoEncontrado() {
        when(restauranteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> restauranteService.findById(99L));
    }

    @Test
    void testSaveComSucesso() {
        RestauranteRequestDTO dto = new RestauranteRequestDTO("Nome", "End", "Cozinha", "8h-18h", 1L);

        TipoUsuario tipo = new TipoUsuario();
        tipo.setId(1L);
        tipo.setNomeTipo("Dono de Restaurante");

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setTipoUsuario(tipo);

        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNome("Nome");

        Restaurante restauranteSalvo = new Restaurante();
        restauranteSalvo.setId(1L);
        restauranteSalvo.setNome("Nome");

        RestauranteRequestDTO dtoRetorno = new RestauranteRequestDTO("Nome", "End", "Cozinha", "8h-18h", 1L);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(restauranteMapper.toEntity(dto, usuario)).thenReturn(restaurante);
        when(restauranteRepository.save(restaurante)).thenReturn(restauranteSalvo);
        when(restauranteMapper.toDTO(restauranteSalvo)).thenReturn(dtoRetorno);

        RestauranteRequestDTO resultado = restauranteService.save(dto);

        assertEquals("Nome", resultado.nome());
    }

    @Test
    void testSaveComUsuarioNaoEncontrado() {
        RestauranteRequestDTO dto = new RestauranteRequestDTO("Nome", "End", "Cozinha", "8h-18h", 99L);
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> restauranteService.save(dto));
    }

    @Test
    void testSaveComUsuarioNaoDono() {
        RestauranteRequestDTO dto = new RestauranteRequestDTO("Nome", "End", "Cozinha", "8h-18h", 1L);

        TipoUsuario tipo = new TipoUsuario();
        tipo.setId(2L);
        tipo.setNomeTipo("Cliente"); //

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setTipoUsuario(tipo); //

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        assertThrows(UnauthorizedException.class, () -> restauranteService.save(dto));
    }


    @Test
    void testUpdateComSucesso() {
        Restaurante atual = new Restaurante();
        atual.setId(1L);
        atual.setNome("Antigo");

        Restaurante novo = new Restaurante();
        novo.setNome("Novo");
        novo.setEndereco("End");
        novo.setHorarioFuncionamento("10-18h");
        novo.setTipoCozinha("Japonesa");
        novo.setDono(new Usuario());

        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(atual));
        when(restauranteRepository.save(any())).thenReturn(atual);

        Restaurante resultado = restauranteService.update(1L, novo);

        assertEquals("Novo", resultado.getNome());
        assertEquals("Japonesa", resultado.getTipoCozinha());
    }

    @Test
    void testUpdateNaoEncontrado() {
        Restaurante novo = new Restaurante();
        when(restauranteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> restauranteService.update(99L, novo));
    }

    @Test
    void testDeleteComSucesso() {
        Restaurante r = new Restaurante();
        r.setId(1L);

        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(r));
        doNothing().when(restauranteRepository).delete(r);

        assertDoesNotThrow(() -> restauranteService.delete(1L));
    }

    @Test
    void testDeleteNaoEncontrado() {
        when(restauranteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> restauranteService.delete(1L));
    }
}
