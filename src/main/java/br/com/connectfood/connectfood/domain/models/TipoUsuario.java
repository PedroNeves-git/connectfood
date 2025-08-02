package br.com.connectfood.connectfood.domain.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TIPO_USUARIO")
public class TipoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false, unique = true)
    private String nomeTipo;

    public TipoUsuario(Long id, String nomeTipo) {}

    public TipoUsuario() {}

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TipoUsuario{" +
                "id=" + id +
                ", nomeTipo='" + nomeTipo + '\'' +
                '}';
    }
}
