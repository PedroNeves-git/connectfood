package br.com.connectfood.connectfood.domain.services;

import br.com.connectfood.connectfood.application.dto.UsuarioRequestDTO;
import br.com.connectfood.connectfood.application.dto.login.LoginRequestDTO;
import br.com.connectfood.connectfood.application.dto.login.LoginResponseDTO;
import br.com.connectfood.connectfood.infrastructure.mapper.UsuarioMapper;
import br.com.connectfood.connectfood.domain.models.Usuario;
import br.com.connectfood.connectfood.domain.repositories.UsuarioRepository;
import br.com.connectfood.connectfood.domain.services.exceptions.BadRequestException;
import br.com.connectfood.connectfood.domain.services.exceptions.ResourceNotFoundException;
import br.com.connectfood.connectfood.domain.services.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

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

    public Usuario findUsuarioById(Long id) {
        return this.usuarioRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não localizado"));
    }


    public void saveUsuario(UsuarioRequestDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
        var save = this.usuarioRepository.save(usuario);
        if (save == 0) {
            throw new BadRequestException("Erro ao salvar usuário: " + usuario.getNome());
        }
    }

    public void updateUsuario(Usuario usuario, long id) {
        boolean atualizado = this.usuarioRepository.update(usuario, id) > 0;
        if (!atualizado) {
            throw new ResourceNotFoundException("Não foi possível atualizar. Usuário com ID " + id + " não encontrado.");
        }
    }

    public void deleteUsuario(Long id) {
        var delete = this.usuarioRepository.delete(id);
        if (delete == 0) {
            throw new ResourceNotFoundException("Usuário com ID " + id + " não localizado");
        }
    }

    public void trocarSenha(Long id, String senhaAntiga, String novaSenha) {
        Usuario usuario = findUsuarioById(id);
        if (!usuario.getSenha().equals(senhaAntiga)) {
            throw new UnauthorizedException("Senha antiga incorreta");
        }
        if(novaSenha.equals(senhaAntiga)) {
            throw new UnauthorizedException("Senha atual, tente uma nova senha");
        }
        usuario.setSenha(novaSenha);
        usuario.setDataUltimaAlteracao(OffsetDateTime.now());
        updateUsuario(usuario, id);
    }

    public LoginResponseDTO autenticar(LoginRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByLogin(dto.login())
                .orElseThrow(() -> new ResourceNotFoundException("Login não encontrado"));

        if (!usuario.getSenha().equals(dto.senha())) {
            throw new UnauthorizedException("Senha incorreta");
        }

        return new LoginResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                "*****",
                usuario.getEmail()
        );
    }

}
