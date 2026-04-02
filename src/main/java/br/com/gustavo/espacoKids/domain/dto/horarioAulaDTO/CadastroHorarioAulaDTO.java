package br.com.gustavo.espacoKids.domain.dto.horarioAulaDTO;

import br.com.gustavo.espacoKids.domain.entity.horarioAula.DiaSemana;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record CadastroHorarioAulaDTO(
        @NotNull Long alunoId,
        @NotNull DiaSemana diaSemana,
        @NotNull LocalTime horaInicio,
        @NotNull LocalTime horaFim) {
}
