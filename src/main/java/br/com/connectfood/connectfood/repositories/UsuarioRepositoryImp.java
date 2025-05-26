package br.com.connectfood.connectfood.repositories;

import br.com.connectfood.connectfood.models.Usuario;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryImp implements UsuarioRepository {

    private final JdbcClient jdbcClient;

    public UsuarioRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return this.jdbcClient
                .sql("Select * from USUARIOS where id = :id")
                .param("id", id)
                .query(Usuario.class)
                .optional();
    }

    @Override
    public List<Usuario> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("SELECT * FROM USUARIOS LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Usuario.class)
                .list();
    }

    @Override
    public Integer save(Usuario usuario) {
        return this.jdbcClient
                .sql("INSERT INTO USUARIOS (nome, email, senha, endereco, data_ultima_alteracao) Values (:nome, :email, :senha, :endereco, :dataUltimaAlteracao)")
                .param("nome", usuario.getNome())
                .param("email", usuario.getEmail())
                .param("senha", usuario.getSenha())
                .param("endereco", usuario.getEndereco())
                .param("dataUltimaAlteracao", usuario.getDataUltimaAlteracao())
                .update();
    }

    @Override
    public Integer update(Usuario usuario, Long id) {
        return this.jdbcClient
                .sql("UPDATE USUARIOS SET nome = :nome, email = :email, senha = :senha, endereco = :endereco, data_ultima_alteracao = :dataUltimaAlteracao where id = :id")
                .param("id", id)
                .param("nome", usuario.getNome())
                .param("email", usuario.getEmail())
                .param("senha", usuario.getSenha())
                .param("endereco", usuario.getEndereco())
                .param("dataUltimaAlteracao", usuario.getDataUltimaAlteracao())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM USUARIOS where id = :id")
                .param("id", id)
                .update();
    }


}
