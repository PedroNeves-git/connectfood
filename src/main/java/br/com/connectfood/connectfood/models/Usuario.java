package br.com.connectfood.connectfood.models;

import br.com.connectfood.connectfood.dto.UsuarioRequestDTO;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Objects;

public class Usuario {

    private Long id;

    private String nome;

    private String email;

    private String senha;

    private OffsetDateTime dataUltimaAlteracao;

    private String endereco;

    public Usuario() {}

    public Usuario(Long id, String nome, String email, String senha, OffsetDateTime  dataUltimaAlteracao, String endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.endereco = endereco;
    }

    public Usuario(UsuarioRequestDTO dto) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.email = dto.email();
        this.senha = dto.senha();
        this.endereco = dto.endereco();
        this.dataUltimaAlteracao = dto.dataUltimaAlteracao();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha) && Objects.equals(dataUltimaAlteracao, usuario.dataUltimaAlteracao) && Objects.equals(endereco, usuario.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senha, dataUltimaAlteracao, endereco);
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
                '}';
    }
}
