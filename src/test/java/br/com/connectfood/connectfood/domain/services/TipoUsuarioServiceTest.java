package br.com.connectfood.connectfood.domain.services;

import br.com.connectfood.connectfood.application.dto.TipoUsuarioDTO;
import br.com.connectfood.connectfood.domain.models.TipoUsuario;
import br.com.connectfood.connectfood.domain.repositories.TipoUsuarioRepository;
import br.com.connectfood.connectfood.domain.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TipoUsuarioServiceTest {

    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private TipoUsuarioService tipoUsuarioService;

    @Test
    void testFindAll() {
        TipoUsuario t1 = new TipoUsuario(1L, "Dono");
        TipoUsuario t2 = new TipoUsuario(2L, "Cliente");

        when(tipoUsuarioRepository.findAll()).thenReturn(List.of(t1, t2));

        List<TipoUsuarioDTO> result = tipoUsuarioService.findAll();

        assertEquals(2, result.size());
        assertEquals("Dono", result.get(0).nome());
        assertEquals("Cliente", result.get(1).nome());
    }

    @Test
    void testFindByIdComSucesso() {
        TipoUsuario tipo = new TipoUsuario(1L, "Dono");
        when(tipoUsuarioRepository.findById(1L)).thenReturn(Optional.of(tipo));

        TipoUsuarioDTO result = tipoUsuarioService.findById(1L);

        assertNotNull(result);
        assertEquals("Dono", result.nome());
    }

    @Test
    void testFindByIdComErro() {
        when(tipoUsuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> tipoUsuarioService.findById(1L));
    }

    @Test
    void testSave() {
        TipoUsuarioDTO dto = new TipoUsuarioDTO(null, "Administrador");
        TipoUsuario tipoSalvo = new TipoUsuario(1L, "Administrador");

        when(tipoUsuarioRepository.save(any())).thenReturn(tipoSalvo);

        TipoUsuarioDTO result = tipoUsuarioService.save(dto);

        assertNotNull(result);
        assertEquals("Administrador", result.nome());
        assertEquals(1L, result.id());
    }

    @Test
    void testUpdateComSucesso() {
        TipoUsuario existente = new TipoUsuario(1L, "Antigo");
        TipoUsuarioDTO novoDTO = new TipoUsuarioDTO(1L, "Novo");

        when(tipoUsuarioRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(tipoUsuarioRepository.save(any())).thenReturn(new TipoUsuario(1L, "Novo"));

        TipoUsuarioDTO atualizado = tipoUsuarioService.update(1L, novoDTO);

        assertEquals("Novo", atualizado.nome());
        assertEquals(1L, atualizado.id());
    }

    @Test
    void testUpdateComErro() {
        TipoUsuarioDTO dto = new TipoUsuarioDTO(1L, "Novo");
        when(tipoUsuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> tipoUsuarioService.update(1L, dto));
    }

    @Test
    void testDeleteComSucesso() {
        when(tipoUsuarioRepository.existsById(1L)).thenReturn(true);
        doNothing().when(tipoUsuarioRepository).deleteById(1L);

        assertDoesNotThrow(() -> tipoUsuarioService.delete(1L));
    }

    @Test
    void testDeleteComErro() {
        when(tipoUsuarioRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> tipoUsuarioService.delete(1L));
    }
}
