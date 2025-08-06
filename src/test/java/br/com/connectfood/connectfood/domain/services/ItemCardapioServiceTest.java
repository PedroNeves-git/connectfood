package br.com.connectfood.connectfood.domain.services;

import br.com.connectfood.connectfood.domain.models.ItemCardapio;
import br.com.connectfood.connectfood.domain.repositories.ItemCardapioRepository;
import br.com.connectfood.connectfood.domain.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemCardapioServiceTest {

    @Mock
    private ItemCardapioRepository repository;

    @InjectMocks
    private ItemCardapioService service;

    @Test
    void testFindAll() {
        ItemCardapio item1 = new ItemCardapio(1L, "Pizza", "Deliciosa", new BigDecimal("25.0"), false, "pizza.jpg", 1L);
        ItemCardapio item2 = new ItemCardapio(2L, "Hamburguer", "Com queijo", new BigDecimal("37.0"), true, "burguer.jpg", 1L);

        when(repository.findAll()).thenReturn(Arrays.asList(item1, item2));

        List<ItemCardapio> result = service.findAll();

        assertEquals(2, result.size());
        assertEquals("Pizza", result.get(0).getNome());
    }

    @Test
    void testFindByIdComSucesso() {
        ItemCardapio item = new ItemCardapio(1L, "Pizza", "Deliciosa", new BigDecimal(39.9), false, "pizza.jpg", 1L);
        when(repository.findById(1L)).thenReturn(Optional.of(item));

        ItemCardapio result = service.findById(1L);

        assertNotNull(result);
        assertEquals("Pizza", result.getNome());
    }

    @Test
    void testFindByIdNaoEncontrado() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(99L));
    }

    @Test
    void testSave() {
        ItemCardapio item = new ItemCardapio(null, "Pizza", "Deliciosa", new BigDecimal(39.9), false, "pizza.jpg", 1L);
        ItemCardapio salvo = new ItemCardapio(1L, "Pizza", "Deliciosa", new BigDecimal(39.9), false, "pizza.jpg", 1L);

        when(repository.save(any())).thenReturn(salvo);

        ItemCardapio result = service.save(item);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testUpdateComSucesso() {
        ItemCardapio existente = new ItemCardapio(1L, "Pizza", "Deliciosa", new BigDecimal(39.9), false, "pizza.jpg", 1L);
        ItemCardapio atualizado = new ItemCardapio(null, "Pizza Calabresa", "Com calabresa", new BigDecimal(42.0), true, "calabresa.jpg", 1L);

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any())).thenReturn(existente);

        ItemCardapio result = service.update(1L, atualizado);

        assertNotNull(result);
        assertEquals("Pizza Calabresa", result.getNome());
    }

    @Test
    void testUpdateNaoEncontrado() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        ItemCardapio novo = new ItemCardapio();

        assertThrows(ResourceNotFoundException.class, () -> service.update(1L, novo));
    }

    @Test
    void testDeleteComSucesso() {
        ItemCardapio item = new ItemCardapio(1L, "Pizza", "Deliciosa", new BigDecimal(39.9), false, "pizza.jpg", 1L);
        when(repository.findById(1L)).thenReturn(Optional.of(item));
        doNothing().when(repository).delete(item);

        assertDoesNotThrow(() -> service.delete(1L));
    }

    @Test
    void testDeleteNaoEncontrado() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.delete(1L));
    }
}
