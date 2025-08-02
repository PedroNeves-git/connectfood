package br.com.connectfood.connectfood.domain.repositories;

import br.com.connectfood.connectfood.domain.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findById(Long id);

    List<Usuario> findAll(int size, int offset);

    Integer save(Usuario usuario);

    Integer update(Usuario usuario, Long id);

    Integer delete(Long id);

    Optional<Usuario> findByLogin(String login);

}
