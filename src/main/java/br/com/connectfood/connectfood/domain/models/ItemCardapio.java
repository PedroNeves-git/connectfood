package br.com.connectfood.connectfood.domain.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_cardapio")
public class ItemCardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    @Column(name = "somente_local")
    private Boolean somenteLocal;

    @Column(name = "caminho_foto")
    private String caminhoFoto;

    @Column(name = "restaurante_id")
    private Long restauranteId;


    public ItemCardapio() {}

    public ItemCardapio(Long id, String nome, String descricao, BigDecimal preco, Boolean somenteLocal, String caminhoFoto, Long restauranteId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.somenteLocal = somenteLocal;
        this.caminhoFoto = caminhoFoto;
        this.restauranteId = restauranteId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Boolean getSomenteLocal() {
        return somenteLocal;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setSomenteLocal(Boolean somenteLocal) {
        this.somenteLocal = somenteLocal;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    @Override
    public String toString() {
        return "ItemCardapio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", somenteLocal=" + somenteLocal +
                ", caminhoFoto='" + caminhoFoto + '\'' +
                ", restauranteId=" + restauranteId +
                '}';
    }

}