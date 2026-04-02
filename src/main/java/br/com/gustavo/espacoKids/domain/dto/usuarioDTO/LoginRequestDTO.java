package br.com.gustavo.espacoKids.domain.dto.usuarioDTO;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO (@NotBlank String login, @NotBlank String senha){
}
