package br.com.gustavo.espacoKids.controller;

import br.com.gustavo.espacoKids.domain.dto.alunoDTO.AlunoDetalhesDTO;
import br.com.gustavo.espacoKids.domain.dto.alunoDTO.CadastroAlunoDTO;
import br.com.gustavo.espacoKids.service.alunoService.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("aluno")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService service;

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
    public ResponseEntity<List<AlunoDetalhesDTO>> buscarAlunosDoResponsavel(@PathVariable Long responsavelId) {
        return ResponseEntity.ok(service.buscarAlunosDoResponsavel(responsavelId));
    }

    @GetMapping("/{responsavelId}/{alunoId}")
    public ResponseEntity<AlunoDetalhesDTO> buscarDetalhesDoAluno(@PathVariable Long responsavelId, @PathVariable Long alunoId) {
        return ResponseEntity.ok(service.buscarDetalhesDoAluno(responsavelId, alunoId));
    }
}
