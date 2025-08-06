package br.com.connectfood.connectfood.domain.services;

import br.com.connectfood.connectfood.application.dto.UsuarioRequestDTO;
import br.com.connectfood.connectfood.application.dto.login.LoginRequestDTO;
import br.com.connectfood.connectfood.application.dto.login.LoginResponseDTO;
import br.com.connectfood.connectfood.domain.models.TipoUsuario;
import br.com.connectfood.connectfood.domain.repositories.TipoUsuarioRepository;
import br.com.connectfood.connectfood.domain.services.exceptions.ConflictException;
import br.com.connectfood.connectfood.infrastructure.mapper.UsuarioMapper;
import br.com.connectfood.connectfood.domain.models.Usuario;
import br.com.connectfood.connectfood.domain.repositories.UsuarioRepository;
import br.com.connectfood.connectfood.domain.services.exceptions.BadRequestException;
import br.com.connectfood.connectfood.domain.services.exceptions.ResourceNotFoundException;
import br.com.connectfood.connectfood.domain.services.exceptions.UnauthorizedException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, TipoUsuarioRepository tipoUsuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public List<Usuario> findAllUsuarios(int page, int size) {
        return usuarioRepository.findAll(PageRequest.of(page - 1, size)).getContent();
    }

    public Usuario findUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não localizado"));
    }

    public void saveUsuario(UsuarioRequestDTO usuarioDTO) {
        if (usuarioRepository.findByLogin(usuarioDTO.login()).isPresent()) {
            throw new ConflictException("Login já está em uso");
        }

        TipoUsuario tipo = tipoUsuarioRepository.findById(usuarioDTO.tipoUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuário não encontrado"));

        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO, tipo);
        Usuario salvo = usuarioRepository.save(usuario);
        if (salvo.getId() == null) {
            throw new BadRequestException("Erro ao salvar usuário: " + usuario.getNome());
        }
    }

    public void updateUsuario(Usuario usuario, long id) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não encontrado"));

        existente.setNome(usuario.getNome());
        existente.setEmail(usuario.getEmail());
        existente.setSenha(usuario.getSenha());
        existente.setEndereco(usuario.getEndereco());
        existente.setDataUltimaAlteracao(usuario.getDataUltimaAlteracao());
        existente.setLogin(usuario.getLogin());
        existente.setTipoUsuario(usuario.getTipoUsuario());

        usuarioRepository.save(existente);
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não localizado"));

        usuarioRepository.delete(usuario);
    }

    public void trocarSenha(Long id, String senhaAntiga, String novaSenha) {
        Usuario usuario = findUsuarioById(id);

        if (!usuario.getSenha().equals(senhaAntiga)) {
            throw new UnauthorizedException("Senha antiga incorreta");
        }

        if (novaSenha.equals(senhaAntiga)) {
            throw new UnauthorizedException("Senha atual, tente uma nova senha");
        }

        usuario.setSenha(novaSenha);
        usuario.setDataUltimaAlteracao(OffsetDateTime.now());

        usuarioRepository.save(usuario);
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
                "*****", // não retornar senha real
                usuario.getEmail()
        );
    }
}
