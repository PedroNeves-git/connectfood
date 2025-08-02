package br.com.connectfood.connectfood.domain.services;

import br.com.connectfood.connectfood.application.dto.TipoUsuarioDTO;
import br.com.connectfood.connectfood.domain.models.TipoUsuario;
import br.com.connectfood.connectfood.domain.repositories.TipoUsuarioRepository;
import br.com.connectfood.connectfood.domain.services.exceptions.ResourceNotFoundException;
import br.com.connectfood.connectfood.infrastructure.mapper.TipoUsuarioMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoUsuarioService {

    private final TipoUsuarioRepository repository;

    public TipoUsuarioService(TipoUsuarioRepository repository) {
        this.repository = repository;
    }

    public List<TipoUsuarioDTO> findAll() {
        return repository.findAll().stream()
                .map(TipoUsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TipoUsuarioDTO findById(Long id) {
        TipoUsuario tipo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuário não encontrado"));
        return TipoUsuarioMapper.toDTO(tipo);
    }

    public TipoUsuarioDTO save(TipoUsuarioDTO dto) {
        TipoUsuario tipo = TipoUsuarioMapper.toEntity(dto);
        TipoUsuario saved = repository.save(tipo);
        return TipoUsuarioMapper.toDTO(saved);
    }

    public TipoUsuarioDTO update(Long id, TipoUsuarioDTO dto) {
        TipoUsuario tipo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuário não encontrado"));

        tipo.setNomeTipo(dto.nome());
        TipoUsuario atualizado = repository.save(tipo);
        return TipoUsuarioMapper.toDTO(atualizado);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tipo de usuário com ID " + id + " não encontrado.");
        }
        repository.deleteById(id);
    }
}
