package br.com.connectfood.connectfood.domain.services;

import br.com.connectfood.connectfood.application.dto.RestauranteRequestDTO;
import br.com.connectfood.connectfood.domain.models.Restaurante;
import br.com.connectfood.connectfood.domain.models.Usuario;
import br.com.connectfood.connectfood.domain.repositories.RestauranteRepository;
import br.com.connectfood.connectfood.domain.repositories.UsuarioRepository;
import br.com.connectfood.connectfood.domain.services.exceptions.ResourceNotFoundException;
import br.com.connectfood.connectfood.domain.services.exceptions.UnauthorizedException;
import br.com.connectfood.connectfood.infrastructure.mapper.RestauranteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;
    private final RestauranteMapper restauranteMapper;

    public RestauranteService(RestauranteRepository restauranteRepository,
                              UsuarioRepository usuarioRepository,
                              RestauranteMapper restauranteMapper) {
        this.restauranteRepository = restauranteRepository;
        this.usuarioRepository = usuarioRepository;
        this.restauranteMapper = restauranteMapper;
    }

    public List<Restaurante> findAll() {
        return restauranteRepository.findAll();
    }

    public Restaurante findById(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante com ID " + id + " não encontrado"));
    }

    public RestauranteRequestDTO save(RestauranteRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.idDono())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + dto.idDono() + " não encontrado"));

        if (!usuario.getTipoUsuario().getNomeTipo().equalsIgnoreCase("Dono de Restaurante")) {
            throw new UnauthorizedException("Usuário não autorizado a cadastrar restaurante.");
        }

        Restaurante restaurante = restauranteMapper.toEntity(dto, usuario);
        Restaurante salvo = restauranteRepository.save(restaurante);

        return restauranteMapper.toDTO(salvo);
    }

    public Restaurante update(Long id, Restaurante novo) {
        Restaurante atual = findById(id);
        atual.setNome(novo.getNome());
        atual.setEndereco(novo.getEndereco());
        atual.setTipoCozinha(novo.getTipoCozinha());
        atual.setHorarioFuncionamento(novo.getHorarioFuncionamento());
        atual.setDono(novo.getDono());
        return restauranteRepository.save(atual);
    }

    public void delete(Long id) {
        Restaurante restaurante = findById(id);
        restauranteRepository.delete(restaurante);
    }
}

