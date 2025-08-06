package br.com.connectfood.connectfood.infrastructure.controllers;

import br.com.connectfood.connectfood.application.dto.TipoUsuarioDTO;
import br.com.connectfood.connectfood.domain.services.TipoUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-usuarios")
public class TipoUsuarioController {

    private final TipoUsuarioService service;

    public TipoUsuarioController(TipoUsuarioService service) {
        this.service = service;
    }
    @Operation(summary = "Lista todos os tipos de usuário")
    @GetMapping
    public ResponseEntity<List<TipoUsuarioDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @Operation(summary = "Busca tipo de usuário pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @Operation(summary = "Cadastra um novo tipo de usuário")
    @PostMapping
    public ResponseEntity<TipoUsuarioDTO> save(@RequestBody TipoUsuarioDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }
    @Operation(summary = "Atualiza tipo de usuário")
    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuarioDTO> update(@PathVariable Long id, @RequestBody TipoUsuarioDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
    @Operation(summary = "Deleta tipo de usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
