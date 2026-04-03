package br.com.gustavo.espacoKids.repository;

import br.com.gustavo.espacoKids.domain.entity.atividade.AtividadeAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface AtividadeAlunoRepository extends JpaRepository<AtividadeAluno, Long> {
    List<AtividadeAluno> findByAlunoId(Long alunoId);
    List<AtividadeAluno> findByDataAtribuicaoBefore(LocalDateTime data);

    @Transactional
    void deleteByDataAtribuicaoBefore(LocalDateTime data);

    boolean existsByBancoAtividadeIdAndAlunoId(Long bancoAtividadeId, Long alunoId);

    @Transactional
    void deleteByBancoAtividadeId(Long bancoAtividadeId);
}
