package br.com.gustavo.espacoKids.domain.dto.responsavelDTO;

import br.com.gustavo.espacoKids.domain.entity.responsavel.Responsavel;

public record ResponsavelDetalhesDTO(Long id, String nome, String telefone) {

    public ResponsavelDetalhesDTO(Responsavel responsavel) {
        this(responsavel.getId(), responsavel.getUsuarioId().getNome(), responsavel.getTelefone());
    }
}
