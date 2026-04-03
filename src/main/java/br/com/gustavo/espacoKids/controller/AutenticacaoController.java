package br.com.gustavo.espacoKids.controller;

import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.AtualizarSenhaDTO;
import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.AtualizarStatusDTO;
import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.AtualizarUsuarioDTO;
import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.CadastroUsuarioDTO;
import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.LoginRequestDTO;
import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.LoginResponseDTO;
import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.UsuarioDetalhesDTO;
import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.UsuarioSelectDTO;
import br.com.gustavo.espacoKids.domain.entity.usuario.Usuario;
import br.com.gustavo.espacoKids.infra.config.security.JwtService;
import br.com.gustavo.espacoKids.service.usuarioService.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/autenticacao")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = authenticationManager.authenticate(authToken); // lança exceção se inválido

        var usuario = (UserDetails) authentication.getPrincipal();
        String token = jwtService.gerarToken(usuario);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/cadastro")
    @PreAuthorize("hasRole('ADM')")          // ← só ADM passa
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid CadastroUsuarioDTO dto,
                                          UriComponentsBuilder uriBuilder) {
        Usuario novo = usuarioService.cadastrar(dto);

        var uri = uriBuilder.path("/autenticacao/cadastro/{id}").buildAndExpand(novo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/senha")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<Void> atualizarSenha(@RequestBody @Valid AtualizarSenhaDTO dto) {
        usuarioService.atualizarSenha(dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuarios-para-senha")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<List<UsuarioSelectDTO>> listarUsuariosParaSenha() {
        return ResponseEntity.ok(usuarioService.listarUsuariosParaSenha());
    }

    @GetMapping("/usuarios")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<List<UsuarioDetalhesDTO>> listarTodosUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodosUsuarios());
    }

    @PutMapping("/usuarios/{id}")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<UsuarioDetalhesDTO> atualizarUsuario(@PathVariable Long id,
                                                               @RequestBody AtualizarUsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(id, dto));
    }

    @PatchMapping("/usuarios/{id}/status")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id,
                                                @RequestBody @Valid AtualizarStatusDTO dto) {
        usuarioService.atualizarStatus(id, dto);
        return ResponseEntity.noContent().build();
    }
}
