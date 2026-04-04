package br.com.gustavo.espacoKids.domain.dto.atividadeDTO;

import br.com.gustavo.espacoKids.domain.entity.atividade.BancoAtividade;

import java.time.LocalDateTime;

public record BancoAtividadeDTO(Long id, String titulo, String descricao, String nomeArquivo,
                                String categoria, LocalDateTime criadoEm) {
    public BancoAtividadeDTO(BancoAtividade atividade) {
        this(atividade.getId(), atividade.getTitulo(), atividade.getDescricao(),
             atividade.getNomeArquivo(), atividade.getCategoria().name(), atividade.getCriadoEm());
    }
}
