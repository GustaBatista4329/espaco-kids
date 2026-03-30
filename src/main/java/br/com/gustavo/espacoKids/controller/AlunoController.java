package br.com.gustavo.espacoKids.controller;


import br.com.gustavo.espacoKids.dto.alunoDTO.AlunoDetalhesDTO;
import br.com.gustavo.espacoKids.dto.alunoDTO.CadastroAlunoDTO;
import br.com.gustavo.espacoKids.service.alunoService.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("aluno") @RequiredArgsConstructor
public class AlunoController {

    private final AlunoService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<CadastroAlunoDTO> cadastrarAluno(@RequestBody CadastroAlunoDTO cadastroAlunoDTO){
        service.cadastrarAlunoDTO(cadastroAlunoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroAlunoDTO);
    }

    @GetMapping("/{responsavelId}")
    public ResponseEntity<List<AlunoDetalhesDTO>> buscarAlunosDoResponsavel(@PathVariable Long responsavelId){
        var aluno = service.buscarAlunosDoResponsavel(responsavelId);
        return ResponseEntity.ok(aluno);
    }

    @GetMapping("/{responsavelId}/{alunoId}")
    public ResponseEntity<AlunoDetalhesDTO> buscarDetalhesDoAluno(@PathVariable Long responsavelId, @PathVariable Long alunoId){
        var alunoDetalhamento = service.buscarDetalhesDoAluno(responsavelId, alunoId);

        return ResponseEntity.ok(alunoDetalhamento);

    }

}
