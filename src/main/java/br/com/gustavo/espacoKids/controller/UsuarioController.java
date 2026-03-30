package br.com.gustavo.espacoKids.controller;

import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.CadastroUsuarioDTO;
import br.com.gustavo.espacoKids.repository.UsuarioRepository;
import br.com.gustavo.espacoKids.service.usuarioService.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository repository;
    private final UsuarioService service;

    @PostMapping
    public ResponseEntity cadastrarUsuario(@RequestBody CadastroUsuarioDTO cadastroUsuarioDTO){
        service.cadastroUsuarioDTO(cadastroUsuarioDTO);
        return ResponseEntity.ok(cadastroUsuarioDTO);
    }
}
