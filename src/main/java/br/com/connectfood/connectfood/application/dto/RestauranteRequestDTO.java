package br.com.connectfood.connectfood.application.dto;


public record RestauranteRequestDTO(
        Long id,
        String nome,
        String endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        Long donoId
) {
}
