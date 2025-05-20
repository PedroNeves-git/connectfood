package br.com.connectfood.connectfood.records.get;


public record UsuarioRecord(
       Long id,
       String nome,
       String email,
       String login,
       String enderecoCompleto
) {}
