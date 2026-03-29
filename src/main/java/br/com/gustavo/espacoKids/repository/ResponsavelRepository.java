package br.com.gustavo.espacoKids.repository;

import br.com.gustavo.espacoKids.entity.responsavel.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
}
