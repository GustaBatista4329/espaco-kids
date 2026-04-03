package br.com.gustavo.espacoKids.domain.dto.usuarioDTO;

import br.com.gustavo.espacoKids.domain.entity.usuario.Perfil;
import br.com.gustavo.espacoKids.domain.entity.usuario.Usuario;

import java.time.LocalDateTime;

public record UsuarioDetalhesDTO(Long id, String nome, String login, Perfil perfil, Boolean ativo, LocalDateTime criadoEm) {
    public UsuarioDetalhesDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getPerfil(), usuario.getAtivo(), usuario.getCriadoEm());
    }
}
