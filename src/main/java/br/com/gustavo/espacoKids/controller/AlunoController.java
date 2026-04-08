package br.com.gustavo.espacoKids.controller;

import br.com.gustavo.espacoKids.domain.dto.alunoDTO.AlunoDetalhesDTO;
import br.com.gustavo.espacoKids.domain.dto.alunoDTO.CadastroAlunoDTO;
import br.com.gustavo.espacoKids.service.alunoService.AlunoService;
import br.com.gustavo.espacoKids.domain.entity.usuario.Perfil;
import br.com.gustavo.espacoKids.domain.entity.usuario.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("aluno")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADM', 'PROFESSORA')")
    public ResponseEntity<List<AlunoDetalhesDTO>> listarTodosAlunos() {
        return ResponseEntity.ok(service.listarTodosAlunos());
    }

    @PreAuthorize("hasRole('ADM')")
    @PostMapping
    public ResponseEntity<AlunoDetalhesDTO> cadastrarAluno(@RequestBody @Valid CadastroAlunoDTO dto) {
        var aluno = service.cadastrarAluno(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{responsavelId}/{alunoId}")
                .buildAndExpand(dto.responsavelId(), aluno.id())
                .toUri();
        return ResponseEntity.created(uri).body(aluno);
    }

    @GetMapping("/{responsavelId}")
    public ResponseEntity<List<AlunoDetalhesDTO>> buscarAlunosDoResponsavel(
            @PathVariable Long responsavelId,
            @AuthenticationPrincipal Usuario usuario) {
        if (usuario.getPerfil() == Perfil.RESPONSAVEL
                && !usuario.getResponsavel().getId().equals(responsavelId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(service.buscarAlunosDoResponsavel(responsavelId));
    }

    @GetMapping("/{responsavelId}/{alunoId}")
    public ResponseEntity<AlunoDetalhesDTO> buscarDetalhesDoAluno(
            @PathVariable Long responsavelId,
            @PathVariable Long alunoId,
            @AuthenticationPrincipal Usuario usuario) {
        if (usuario.getPerfil() == Perfil.RESPONSAVEL
                && !usuario.getResponsavel().getId().equals(responsavelId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(service.buscarDetalhesDoAluno(responsavelId, alunoId));
    }
}
