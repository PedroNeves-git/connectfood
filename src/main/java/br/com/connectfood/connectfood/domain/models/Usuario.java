package br.com.connectfood.connectfood.domain.models;

import br.com.connectfood.connectfood.application.dto.UsuarioRequestDTO;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String senha;

    private OffsetDateTime dataUltimaAlteracao;

    private String endereco;

    private String login;

    @ManyToOne
    @JoinColumn(name = "tipo_usuario_id")
    private TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Restaurante> restaurantes;

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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
