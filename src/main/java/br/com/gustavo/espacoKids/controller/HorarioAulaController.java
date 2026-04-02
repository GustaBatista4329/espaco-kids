package br.com.gustavo.espacoKids.controller;

import br.com.gustavo.espacoKids.domain.dto.horarioAulaDTO.CadastroHorarioAulaDTO;
import br.com.gustavo.espacoKids.domain.dto.horarioAulaDTO.DetalhesHorarioAulaDTO;
import br.com.gustavo.espacoKids.service.horarioAulaService.HorarioAulaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("horario")
@RequiredArgsConstructor
public class HorarioAulaController {

    private final HorarioAulaService service;

    @PostMapping("/cadastrar")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<DetalhesHorarioAulaDTO> cadastrarHorarioAula(@RequestBody @Valid CadastroHorarioAulaDTO dto) {
        var horario = service.cadastrarHorarioAula(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(horario);
    }

    @GetMapping("/todos")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<List<DetalhesHorarioAulaDTO>> buscarTodosHorariosAula(){
        var horariosAula = service.buscarTodosHorariosAula();
        return ResponseEntity.ok(horariosAula);
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<List<DetalhesHorarioAulaDTO>> buscarHorariosAulaPorAluno(@PathVariable Long id){
        var horarios = service.buscarHorariosPorAluno(id);
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/responsavel/{id}")
    public ResponseEntity<List<DetalhesHorarioAulaDTO>> buscarHorariosAulaPorResponsavel(@PathVariable Long id){
        var horarios = service.buscarHorariosPorResponsavel(id);
        return ResponseEntity.ok(horarios);
    }
}
