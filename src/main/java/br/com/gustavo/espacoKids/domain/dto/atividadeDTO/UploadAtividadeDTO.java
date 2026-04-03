package br.com.gustavo.espacoKids.domain.dto.atividadeDTO;

import jakarta.validation.constraints.NotBlank;

public record UploadAtividadeDTO(@NotBlank String titulo, String descricao) {
}
