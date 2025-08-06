package br.com.connectfood.connectfood.infrastructure.mapper;

import br.com.connectfood.connectfood.application.dto.UsuarioRequestDTO;
import br.com.connectfood.connectfood.domain.models.TipoUsuario;
import br.com.connectfood.connectfood.domain.models.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto, TipoUsuario tipoUsuario) {
        if (dto == null) return null;

        Usuario usuario = new Usuario(
                dto.id(),
                dto.nome(),
                dto.email(),
                dto.senha(),
                dto.dataUltimaAlteracao(),
                dto.endereco(),
                dto.login()
        );
        usuario.setTipoUsuario(tipoUsuario);
        return usuario;
    }
}
