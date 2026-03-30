package br.com.gustavo.espacoKids.repository;

import br.com.gustavo.espacoKids.entity.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByResponsavelId(Long responsavelId);

    Optional<Aluno> findByIdAndResponsavelId(Long id, Long responsavelId);

}
