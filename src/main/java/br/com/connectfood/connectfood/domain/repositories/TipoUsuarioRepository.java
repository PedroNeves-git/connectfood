package br.com.connectfood.connectfood.domain.repositories;

import br.com.connectfood.connectfood.domain.models.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoUsuarioRepository  extends JpaRepository<TipoUsuario, Long> {
    Optional<TipoUsuario> findByNomeTipo(String nomeTipo);
}
