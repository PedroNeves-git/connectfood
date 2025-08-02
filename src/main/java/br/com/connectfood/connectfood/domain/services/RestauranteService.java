package br.com.connectfood.connectfood.domain.services;

import br.com.connectfood.connectfood.domain.models.Restaurante;
import br.com.connectfood.connectfood.domain.repositories.RestauranteRepository;
import br.com.connectfood.connectfood.domain.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public List<Restaurante> findAll() {
        return restauranteRepository.findAll();
    }

    public Restaurante findById(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante com ID " + id + " não encontrado"));
    }

    public Restaurante save(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    public Restaurante update(Long id, Restaurante novo) {
        Restaurante atual = findById(id);
        atual.setNome(novo.getNome());
        atual.setEndereco(novo.getEndereco());
        atual.setTipoCozinha(novo.getTipoCozinha());
        atual.setHorarioFuncionamento(novo.getHorarioFuncionamento());
        atual.setDonoId(novo.getDonoId());
        return restauranteRepository.save(atual);
    }

    public void delete(Long id) {
        Restaurante restaurante = findById(id);
        restauranteRepository.delete(restaurante);
    }
}
