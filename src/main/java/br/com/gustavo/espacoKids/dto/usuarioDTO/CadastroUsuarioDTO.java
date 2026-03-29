package br.com.gustavo.espacoKids.dto.usuarioDTO;

import br.com.gustavo.espacoKids.entity.usuario.Perfil;

public record CadastroUsuarioDTO (String nome,
                                  String email,
                                  String senha_hash,
                                  Perfil perfil){

}
