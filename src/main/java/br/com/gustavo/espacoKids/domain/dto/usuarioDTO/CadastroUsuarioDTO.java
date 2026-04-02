package br.com.gustavo.espacoKids.domain.dto.usuarioDTO;

import br.com.gustavo.espacoKids.domain.entity.usuario.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroUsuarioDTO(@NotBlank String nome,
                                 @NotBlank String login,
                                 @NotBlank String senha,
                                 @NotNull Perfil perfil) {
}
