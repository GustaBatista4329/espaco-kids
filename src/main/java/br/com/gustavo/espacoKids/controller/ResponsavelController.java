package br.com.gustavo.espacoKids.controller;

import br.com.gustavo.espacoKids.dto.responsavelDTO.CadastroResponsavelDTO;
import br.com.gustavo.espacoKids.entity.responsavel.Responsavel;
import br.com.gustavo.espacoKids.repository.ResponsavelRepository;
import br.com.gustavo.espacoKids.service.responsavelService.ResponsavelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("responsavel") @RequiredArgsConstructor
public class ResponsavelController {

    private final ResponsavelRepository repository;
    private final ResponsavelService service;

    @GetMapping
    public List<Responsavel> listarTodosResponsaveis(){
        return repository.findAll();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CadastroResponsavelDTO> cadastrarResponsavel(@RequestBody CadastroResponsavelDTO cadastroResponsavelDTO){
        service.cadastrarResponsavel(cadastroResponsavelDTO);

        return ResponseEntity.ok(cadastroResponsavelDTO);
    }

}
