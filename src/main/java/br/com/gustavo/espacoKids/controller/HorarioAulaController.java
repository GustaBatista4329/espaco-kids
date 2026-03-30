package br.com.gustavo.espacoKids.controller;

import br.com.gustavo.espacoKids.domain.dto.horarioAulaDTO.CadastroHorarioAulaDTO;
import br.com.gustavo.espacoKids.service.horarioAulaService.HorarioAulaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("horario") @RequiredArgsConstructor
public class HorarioAulaController {

    private final HorarioAulaService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<CadastroHorarioAulaDTO> cadastrarHorarioAula(@RequestBody CadastroHorarioAulaDTO cadastroHorarioAulaDTO){

        var horarioAula = service.cadastrarHorarioAula(cadastroHorarioAulaDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroHorarioAulaDTO);
    }

}
