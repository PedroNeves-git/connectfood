package br.com.connectfood.connectfood.infrastructure.controllers;

import br.com.connectfood.connectfood.application.dto.AlteracaoSenhaDTO;
import br.com.connectfood.connectfood.application.dto.UsuarioRequestDTO;
import br.com.connectfood.connectfood.application.dto.login.LoginRequestDTO;
import br.com.connectfood.connectfood.application.dto.login.LoginResponseDTO;
import br.com.connectfood.connectfood.domain.models.Usuario;
import br.com.connectfood.connectfood.domain.services.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    public UsuarioController(final UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // http://localhost:8080/usuarios/1
    // http://localhost:8080/usuarios?page=1

    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsuarios(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        logger.info("Foi acessado o endpoint de usuarios /usuarios");
        var usuarios = this.usuarioService.findAllUsuarios(page, size);
        return ResponseEntity.ok(usuarios);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findUsuarioById(@PathVariable("id") Long id) {
        logger.info("/usuarios/" + id);
        var usuario = this.usuarioService.findUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Void> saveUsuario(
           @Valid @RequestBody UsuarioRequestDTO usuario
    ){
        logger.info("POST -> /usuarios/");
        this.usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUsuario(
            @PathVariable("id") Long id,
            @RequestBody Usuario usuario
    ){
        logger.info("PUT -> /usuarios/");
        this.usuarioService.updateUsuario(usuario, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(
            @PathVariable("id") Long id
    ){
        logger.info("DELETE -> /usuarios/");
        this.usuarioService.deleteUsuario(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/senha")
    public ResponseEntity<Void> trocarSenha(@PathVariable Long id, @RequestBody AlteracaoSenhaDTO dto) {
        usuarioService.trocarSenha(id, dto.senhaAntiga(), dto.novaSenha());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        var response = usuarioService.autenticar(dto);
        return ResponseEntity.ok(response);
    }


}
