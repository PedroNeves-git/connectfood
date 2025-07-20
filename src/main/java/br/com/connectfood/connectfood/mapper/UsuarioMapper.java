package br.com.connectfood.connectfood.mapper;

import br.com.connectfood.connectfood.dto.UsuarioRequestDTO;
import br.com.connectfood.connectfood.models.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) return null;

        return new Usuario(
                dto.id(),
                dto.nome(),
                dto.email(),
                dto.senha(),
                dto.dataUltimaAlteracao(),
                dto.endereco(),
                dto.login()
        );
    }
}
