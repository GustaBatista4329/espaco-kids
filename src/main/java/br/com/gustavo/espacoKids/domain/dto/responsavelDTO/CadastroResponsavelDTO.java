package br.com.gustavo.espacoKids.domain.dto.responsavelDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroResponsavelDTO(@NotNull Long usuarioId, @NotBlank String telefone) {
}
