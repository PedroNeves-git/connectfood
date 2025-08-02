package br.com.connectfood.connectfood.application.dto;

import java.math.BigDecimal;

public record ItemCardapioRequestDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        boolean apenasNoLocal,
        String caminhoFoto,
        Long restauranteId
) {
}
