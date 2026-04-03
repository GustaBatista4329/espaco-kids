package br.com.gustavo.espacoKids.repository;

import br.com.gustavo.espacoKids.domain.entity.atividade.BancoAtividade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BancoAtividadeRepository extends JpaRepository<BancoAtividade, Long> {
    List<BancoAtividade> findAllByOrderByCriadoEmDesc();
    boolean existsByNomeArquivo(String nomeArquivo);
}
