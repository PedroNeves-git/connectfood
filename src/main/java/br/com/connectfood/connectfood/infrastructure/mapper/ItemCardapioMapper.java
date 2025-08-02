package br.com.connectfood.connectfood.infrastructure.mapper;

import br.com.connectfood.connectfood.application.dto.ItemCardapioRequestDTO;
import br.com.connectfood.connectfood.domain.models.ItemCardapio;

public class ItemCardapioMapper {

    public static ItemCardapio toEntity(ItemCardapioRequestDTO dto) {
        if (dto == null) return null;

        return new ItemCardapio(
                dto.id(),
                dto.nome(),
                dto.descricao(),
                dto.preco(),
                dto.apenasNoLocal(),
                dto.caminhoFoto(),
                dto.restauranteId()
        );
    }

}
