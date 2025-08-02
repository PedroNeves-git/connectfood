package br.com.connectfood.connectfood.infrastructure.controllers;

import br.com.connectfood.connectfood.domain.models.ItemCardapio;
import br.com.connectfood.connectfood.domain.services.ItemCardapioService;
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

    @GetMapping
    public ResponseEntity<List<ItemCardapio>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapio> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ItemCardapio> save(@RequestBody ItemCardapio item) {
        return new ResponseEntity<>(service.save(item), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapio> update(@PathVariable Long id, @RequestBody ItemCardapio item) {
        return ResponseEntity.ok(service.update(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
