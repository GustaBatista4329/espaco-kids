package br.com.gustavo.espacoKids.controller;

import br.com.gustavo.espacoKids.dto.usuarioDTO.CadastroUsuarioDTO;
import br.com.gustavo.espacoKids.entity.usuario.Usuario;
import br.com.gustavo.espacoKids.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository repository;

    @PostMapping
    public void cadastrarUsuario(@RequestBody CadastroUsuarioDTO cadastroUsuarioDTO){
        var usuario = new Usuario(cadastroUsuarioDTO);
        repository.save(usuario);
    }
}
