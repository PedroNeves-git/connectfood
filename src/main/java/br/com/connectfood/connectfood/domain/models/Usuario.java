package br.com.connectfood.connectfood.domain.models;

import br.com.connectfood.connectfood.application.dto.UsuarioRequestDTO;

import java.time.OffsetDateTime;

public class Usuario {

    private Long id;

    private String nome;

    private String email;

    private String senha;

    private OffsetDateTime dataUltimaAlteracao;

    private String endereco;

    private String login;

    public Usuario() {}

    public Usuario(Long id, String nome, String email, String senha, OffsetDateTime  dataUltimaAlteracao, String endereco, String login) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.endereco = endereco;
        this.login = login;
    }

    public Usuario(UsuarioRequestDTO dto) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.email = dto.email();
        this.senha = dto.senha();
        this.endereco = dto.endereco();
        this.dataUltimaAlteracao = dto.dataUltimaAlteracao();
        this.login = dto.login();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public OffsetDateTime  getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(OffsetDateTime  dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", dataUltimaAlteracao=" + dataUltimaAlteracao +
                ", endereco='" + endereco + '\'' +
                ", login='" + login + '\''+
                '}';
    }
}
