package br.com.gustavo.espacoKids.domain.dto.alunoDTO;

import br.com.gustavo.espacoKids.domain.entity.aluno.Aluno;

import java.time.LocalDate;

public record AlunoDetalhesDTO(Long id,
                               String nome,
                               LocalDate dataNascimento,
                               String serie) {


    public AlunoDetalhesDTO(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getDataNascimento(), aluno.getSerie());
    }
}
