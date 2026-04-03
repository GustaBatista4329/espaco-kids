package br.com.gustavo.espacoKids.domain.dto.atividadeDTO;

import jakarta.validation.constraints.NotNull;

public record AtribuirAtividadeDTO(@NotNull Long bancoAtividadeId, @NotNull Long alunoId) {
}
