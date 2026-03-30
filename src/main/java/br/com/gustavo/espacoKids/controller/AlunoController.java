package br.com.gustavo.espacoKids.controller;


import br.com.gustavo.espacoKids.dto.alunoDTO.CadastroAlunoDTO;
import br.com.gustavo.espacoKids.service.alunoService.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("aluno") @RequiredArgsConstructor
public class AlunoController {

    private final AlunoService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<CadastroAlunoDTO> cadastrarAluno(@RequestBody CadastroAlunoDTO cadastroAlunoDTO){
        service.cadastrarAlunoDTO(cadastroAlunoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroAlunoDTO);
    }

}
