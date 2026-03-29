package br.com.gustavo.espacoKids.repository;

import br.com.gustavo.espacoKids.entity.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
