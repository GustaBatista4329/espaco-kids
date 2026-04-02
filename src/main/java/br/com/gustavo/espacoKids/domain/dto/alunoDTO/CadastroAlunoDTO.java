package br.com.gustavo.espacoKids.domain.dto.alunoDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record CadastroAlunoDTO(
        @NotNull Long responsavelId,
        @NotBlank String nome,
        @NotNull @Past LocalDate dataNascimento,
        @NotBlank String serie) {
}
