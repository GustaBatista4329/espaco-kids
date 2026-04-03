package br.com.gustavo.espacoKids.domain.dto.usuarioDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AtualizarSenhaDTO(@NotNull Long usuarioId,
                                @NotBlank @Size(min = 6) String novaSenha) {
}
