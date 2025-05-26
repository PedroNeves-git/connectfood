package br.com.connectfood.connectfood.services;

import br.com.connectfood.connectfood.dto.UsuarioRequestDTO;
import br.com.connectfood.connectfood.mapper.UsuarioMapper;
import br.com.connectfood.connectfood.models.Usuario;
import br.com.connectfood.connectfood.repositories.UsuarioRepository;
import br.com.connectfood.connectfood.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAllUsuarios(int page, int size) {
        int offset = (page - 1) * size;
        return this.usuarioRepository.findAll(size, offset);
    }

    public Optional<Usuario> findUsuarioById(Long id) {
        return Optional.ofNullable(this.usuarioRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não localizado")));
    }

    public void saveUsuario(UsuarioRequestDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
        var save = this.usuarioRepository.save(usuario);
        Assert.state(save == 1, "Erro ao salvar Usuário " + usuario.getNome());
    }

    public void updateUsuario(Usuario usuario, long id) {
        var update = this.usuarioRepository.update(usuario , id);
        if (update == 0) {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    public void deleteUsuario(Long id) {
        var delete = this.usuarioRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
}
