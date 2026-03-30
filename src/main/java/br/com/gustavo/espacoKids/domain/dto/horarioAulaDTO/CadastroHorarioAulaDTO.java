package br.com.gustavo.espacoKids.domain.dto.horarioAulaDTO;

import br.com.gustavo.espacoKids.domain.entity.horarioAula.DiaSemana;

import java.time.LocalTime;

public record CadastroHorarioAulaDTO(Long alunoId, DiaSemana diaSemana, LocalTime horaInicio, LocalTime horaFim) {
}
