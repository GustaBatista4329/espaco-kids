package br.com.gustavo.espacoKids.domain.dto.usuarioDTO;

import br.com.gustavo.espacoKids.domain.entity.usuario.Perfil;

public record AtualizarUsuarioDTO(String nome, String login, Perfil perfil) {
}
