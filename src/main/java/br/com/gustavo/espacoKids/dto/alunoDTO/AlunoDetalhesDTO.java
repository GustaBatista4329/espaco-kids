package br.com.gustavo.espacoKids.dto.alunoDTO;

import br.com.gustavo.espacoKids.entity.aluno.Aluno;

import java.time.LocalDate;

public record AlunoDetalhesDTO(Long id,
                               String nome,
                               LocalDate dataNascimento,
                               String serie) {


    public AlunoDetalhesDTO(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getDataNascimento(), aluno.getSerie());
    }
}
