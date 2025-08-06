package br.com.connectfood.connectfood.application.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

public record UsuarioRequestDTO(

         Long id,
        @NotNull(message = "O nome do usuario deve ser preenchido")
         String nome,

         String email,
        @NotNull(message = "a senha não pode ser nula")
         String senha,

        OffsetDateTime dataUltimaAlteracao,

         String endereco,
         @NotNull(message = "O login deve ser preenchido")
         String login,
         @NotNull(message = "O tipo do usuário deve ser preenchido")
         Long tipoUsuarioId
) {
}
