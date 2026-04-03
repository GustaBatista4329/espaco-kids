package br.com.gustavo.espacoKids.domain.dto.usuarioDTO;

import jakarta.validation.constraints.NotNull;

public record AtualizarStatusDTO(@NotNull Boolean ativo) {
}
