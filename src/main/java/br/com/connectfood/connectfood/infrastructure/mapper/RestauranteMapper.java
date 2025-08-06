package br.com.connectfood.connectfood.infrastructure.mapper;

import br.com.connectfood.connectfood.application.dto.RestauranteRequestDTO;
import br.com.connectfood.connectfood.domain.models.Restaurante;
import br.com.connectfood.connectfood.domain.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public class RestauranteMapper {

    public Restaurante toEntity(RestauranteRequestDTO dto, Usuario dono) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(dto.nome());
        restaurante.setEndereco(dto.endereco());
        restaurante.setTipoCozinha(dto.tipoCozinha());
        restaurante.setHorarioFuncionamento(dto.horarioFuncionamento());
        restaurante.setDono(dono);
        return restaurante;
    }

    public RestauranteRequestDTO toDTO(Restaurante restaurante) {
        return new RestauranteRequestDTO(
                restaurante.getNome(),
                restaurante.getEndereco(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                restaurante.getDono() != null ? restaurante.getDono().getId() : null
        );
    }
}


