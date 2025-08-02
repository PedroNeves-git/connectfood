package br.com.connectfood.connectfood.infrastructure.mapper;

import br.com.connectfood.connectfood.application.dto.UsuarioRequestDTO;
import br.com.connectfood.connectfood.domain.models.Usuario;

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
