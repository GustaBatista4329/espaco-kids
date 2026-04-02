package br.com.gustavo.espacoKids.repository;

import br.com.gustavo.espacoKids.domain.dto.horarioAulaDTO.DetalhesHorarioAulaDTO;
import br.com.gustavo.espacoKids.domain.entity.horarioAula.HorarioAula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HorarioAulaRepository extends JpaRepository<HorarioAula, Long> {
    @Query("""
            SELECT new br.com.gustavo.espacoKids.domain.dto.horarioAulaDTO.DetalhesHorarioAulaDTO(
                a.nome, u.nome, h.diaSemana, h.horaInicio, h.horaFim
            )
            FROM HorarioAula h
            JOIN h.aluno a
            JOIN a.responsavel r
            JOIN r.usuarioId u
            WHERE h.ativo = true
            """)
    List<DetalhesHorarioAulaDTO> buscarTodosHorarios();

    @Query("""
            SELECT new br.com.gustavo.espacoKids.domain.dto.horarioAulaDTO.DetalhesHorarioAulaDTO(
                a.nome, u.nome, h.diaSemana, h.horaInicio, h.horaFim
            )
            FROM HorarioAula h
            JOIN h.aluno a
            JOIN a.responsavel r
            JOIN r.usuarioId u
            WHERE h.ativo = true AND a.id = :alunoId
            """)
    List<DetalhesHorarioAulaDTO> buscarHorariosPorAlunoId(Long alunoId);

    @Query("""
            SELECT new br.com.gustavo.espacoKids.domain.dto.horarioAulaDTO.DetalhesHorarioAulaDTO(
                a.nome, u.nome, h.diaSemana, h.horaInicio, h.horaFim
            )
            FROM HorarioAula h
            JOIN h.aluno a
            JOIN a.responsavel r
            JOIN r.usuarioId u
            WHERE h.ativo = true AND r.id = :responsavelId
            """)
    List<DetalhesHorarioAulaDTO> buscarHorariosPorResponsavelId(Long responsavelId);
}
