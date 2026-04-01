package br.com.gustavo.espacoKids.domain.dto.usuarioDTO;

import br.com.gustavo.espacoKids.domain.entity.usuario.Perfil;

public record CadastroUsuarioDTO (String nome,
                                  String login,
                                  String senha_hash,
                                  Perfil perfil){

}
