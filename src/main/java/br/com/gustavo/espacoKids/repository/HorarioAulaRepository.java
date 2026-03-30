package br.com.gustavo.espacoKids.repository;

import br.com.gustavo.espacoKids.domain.entity.horarioAula.HorarioAula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioAulaRepository extends JpaRepository<HorarioAula, Long> {
}
