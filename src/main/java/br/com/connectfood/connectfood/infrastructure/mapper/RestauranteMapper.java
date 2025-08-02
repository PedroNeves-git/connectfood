package br.com.connectfood.connectfood.infrastructure.mapper;

import br.com.connectfood.connectfood.application.dto.RestauranteRequestDTO;
import br.com.connectfood.connectfood.domain.models.Restaurante;
import br.com.connectfood.connectfood.application.dto.RestauranteRequestDTO;

public class RestauranteMapper {
    public static Restaurante toEntity(RestauranteRequestDTO dto) {
        if (dto == null) return null;

        return new Restaurante(
                dto.id(),
                dto.nome(),
                dto.endereco(),
                dto.tipoCozinha(),
                dto.horarioFuncionamento(),
                dto.donoId()
        );
    }
}
