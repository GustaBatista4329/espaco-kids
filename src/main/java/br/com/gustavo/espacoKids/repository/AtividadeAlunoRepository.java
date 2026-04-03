package br.com.gustavo.espacoKids.repository;

import br.com.gustavo.espacoKids.domain.entity.atividade.AtividadeAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AtividadeAlunoRepository extends JpaRepository<AtividadeAluno, Long> {
    List<AtividadeAluno> findByAlunoId(Long alunoId);
    List<AtividadeAluno> findByAlunoIdOrderByDataAtribuicaoDesc(Long alunoId);

    boolean existsByBancoAtividadeIdAndAlunoId(Long bancoAtividadeId, Long alunoId);

    @Transactional
    void deleteByBancoAtividadeId(Long bancoAtividadeId);
}
