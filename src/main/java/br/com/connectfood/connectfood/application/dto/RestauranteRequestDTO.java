package br.com.connectfood.connectfood.application.dto;


import jakarta.validation.constraints.NotBlank;

public record RestauranteRequestDTO(
        @NotBlank(message = "O nome do restaurante é obrigatório")
        String nome,

        @NotBlank(message = "O endereço é obrigatório")
        String endereco,

        @NotBlank(message = "O tipo de cozinha é obrigatório")
        String tipoCozinha,

        @NotBlank(message = "O horário de funcionamento é obrigatório")
        String horarioFuncionamento,
        Long idDono
) {
}
