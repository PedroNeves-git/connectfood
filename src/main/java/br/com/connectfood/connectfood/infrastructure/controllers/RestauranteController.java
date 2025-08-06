package br.com.connectfood.connectfood.infrastructure.controllers;

import br.com.connectfood.connectfood.application.dto.RestauranteRequestDTO;
import br.com.connectfood.connectfood.domain.models.Restaurante;
import br.com.connectfood.connectfood.domain.services.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @Operation(summary = "Lista todos os restaurantes")
    @GetMapping
    public ResponseEntity<List<Restaurante>> findAll() {
        return ResponseEntity.ok(restauranteService.findAll());
    }

    @Operation(summary = "Lista restaurante pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> findById(@PathVariable Long id) {
        return ResponseEntity.ok(restauranteService.findById(id));
    }

    @Operation(summary = "Cadastra um novo restaurante")
    @PostMapping
    public ResponseEntity<RestauranteRequestDTO> save(@RequestBody @Valid RestauranteRequestDTO dto) {
        RestauranteRequestDTO restauranteSalvo = restauranteService.save(dto);
        return new ResponseEntity<>(restauranteSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza os dados de um restaurante")
    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> update(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        return ResponseEntity.ok(restauranteService.update(id, restaurante));
    }

    @Operation(summary = "Deleta um restaurante")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restauranteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

