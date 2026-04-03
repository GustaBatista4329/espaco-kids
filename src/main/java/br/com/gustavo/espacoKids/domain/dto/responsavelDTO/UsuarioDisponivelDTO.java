package br.com.gustavo.espacoKids.domain.dto.responsavelDTO;

import br.com.gustavo.espacoKids.domain.entity.usuario.Usuario;

public record UsuarioDisponivelDTO(Long id, String nome, String login) {
    public UsuarioDisponivelDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getLogin());
    }
}