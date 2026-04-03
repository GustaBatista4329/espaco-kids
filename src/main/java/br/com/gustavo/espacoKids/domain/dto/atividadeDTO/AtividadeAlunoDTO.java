package br.com.gustavo.espacoKids.domain.dto.atividadeDTO;

import br.com.gustavo.espacoKids.domain.entity.atividade.AtividadeAluno;

import java.time.LocalDateTime;

public record AtividadeAlunoDTO(Long id, Long bancoAtividadeId, String titulo, String descricao,
                                String nomeArquivo, LocalDateTime dataAtribuicao) {
    public AtividadeAlunoDTO(AtividadeAluno atividadeAluno) {
        this(
                atividadeAluno.getId(),
                atividadeAluno.getBancoAtividade().getId(),
                atividadeAluno.getBancoAtividade().getTitulo(),
                atividadeAluno.getBancoAtividade().getDescricao(),
                atividadeAluno.getBancoAtividade().getNomeArquivo(),
                atividadeAluno.getDataAtribuicao()
        );
    }
}
