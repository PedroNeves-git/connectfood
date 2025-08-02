package br.com.connectfood.connectfood.infrastructure.mapper;

import br.com.connectfood.connectfood.application.dto.TipoUsuarioDTO;
import br.com.connectfood.connectfood.domain.models.TipoUsuario;

public class TipoUsuarioMapper {
    public static TipoUsuario toEntity(TipoUsuarioDTO dto) {
        if (dto == null) return null;
        return new TipoUsuario(dto.id(), dto.nome());
    }

    public static TipoUsuarioDTO toDTO(TipoUsuario entity) {
        if (entity == null) return null;
        return new TipoUsuarioDTO(entity.getId(), entity.getNomeTipo());
    }
}
