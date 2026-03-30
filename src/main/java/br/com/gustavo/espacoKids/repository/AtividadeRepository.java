package br.com.gustavo.espacoKids.repository;

import br.com.gustavo.espacoKids.domain.entity.atividade.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}
