package br.com.gustavo.espacoKids.domain.dto.alunoDTO;

import java.time.LocalDate;

public record CadastroAlunoDTO(Long responsavelId,
                               String nome,
                               LocalDate dataNascimento,
                               String serie) {
}
