package br.com.gustavo.espacoKids.controller;

import br.com.gustavo.espacoKids.domain.dto.responsavelDTO.CadastroResponsavelDTO;
import br.com.gustavo.espacoKids.domain.dto.responsavelDTO.ResponsavelDetalhesDTO;
import br.com.gustavo.espacoKids.domain.dto.responsavelDTO.UsuarioDisponivelDTO;
import br.com.gustavo.espacoKids.service.responsavelService.ResponsavelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("responsavel")
@RequiredArgsConstructor
public class ResponsavelController {

    private final ResponsavelService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADM', 'PROFESSORA')")
    public ResponseEntity<List<ResponsavelDetalhesDTO>> listarTodosResponsaveis() {
        return ResponseEntity.ok(service.listarTodosResponsaveis());
    }

    @PreAuthorize("hasRole('ADM')")
    @PostMapping
    public ResponseEntity<ResponsavelDetalhesDTO> cadastrarResponsavel(@RequestBody @Valid CadastroResponsavelDTO dto) {
        var responsavel = service.cadastrarResponsavel(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responsavel.id())
                .toUri();
        return ResponseEntity.created(uri).body(responsavel);
    }

    @GetMapping("/usuarios-disponiveis")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<List<UsuarioDisponivelDTO>> listarUsuariosDisponiveis() {
        return ResponseEntity.ok(service.listarUsuariosDisponiveis());
    }
}
