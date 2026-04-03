package br.com.gustavo.espacoKids.domain.dto.usuarioDTO;

import br.com.gustavo.espacoKids.domain.entity.usuario.Usuario;

public record UsuarioSelectDTO(Long id, String nome, String login, String perfil) {
    public UsuarioSelectDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getPerfil().name());
    }
}
