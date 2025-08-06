package br.com.connectfood.connectfood.infrastructure.controllers;

import br.com.connectfood.connectfood.domain.models.ItemCardapio;
import br.com.connectfood.connectfood.domain.services.ItemCardapioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-cardapio")
public class ItemCardapioController {

    private final ItemCardapioService service;

    public ItemCardapioController(ItemCardapioService service) {
        this.service = service;
    }

    @Operation(summary = "Lista todos os itens do cardápio")
    @GetMapping
    public ResponseEntity<List<ItemCardapio>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Busca um item do cardápio pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapio> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Cadastra um novo item no cardápio")
    @PostMapping
    public ResponseEntity<ItemCardapio> save(@RequestBody ItemCardapio item) {
        return new ResponseEntity<>(service.save(item), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza um item do cardápio")
    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapio> update(@PathVariable Long id, @RequestBody ItemCardapio item) {
        return ResponseEntity.ok(service.update(id, item));
    }

    @Operation(summary = "Remove um item do cardápio")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
