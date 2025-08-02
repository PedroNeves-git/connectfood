package br.com.connectfood.connectfood.domain.models;

import jakarta.persistence.*;

@Entity
@Table(name = "RESTAURANTES")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String endereco;

    @Column(name = "tipo_cozinha")
    private String tipoCozinha;

    @Column(name = "horario_funcionamento")
    private String horarioFuncionamento;

    @Column(name = "dono_id")
    private Long donoId;

    public Restaurante() {}

    public Restaurante(Long id, String nome, String endereco, String tipoCozinha, String horarioFuncionamento, Long donoId) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.donoId = donoId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public Long getDonoId() {
        return donoId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public void setDonoId(Long donoId) {
        this.donoId = donoId;
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", tipoCozinha='" + tipoCozinha + '\'' +
                ", horarioFuncionamento='" + horarioFuncionamento + '\'' +
                ", donoId=" + donoId +
                '}';
    }
}

