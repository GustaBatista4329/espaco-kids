package br.com.gustavo.espacoKids.service.usuarioService;

import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.CadastroUsuarioDTO;
import br.com.gustavo.espacoKids.domain.entity.usuario.Usuario;
import br.com.gustavo.espacoKids.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encriptador;

    public CadastroUsuarioDTO cadastroUsuarioDTO(CadastroUsuarioDTO cadastroUsuarioDTO){
        var usuario = new Usuario(cadastroUsuarioDTO);
        usuario.setSenhaHash(encriptador.encode(usuario.getSenhaHash()));
        repository.save(usuario);

        return cadastroUsuarioDTO;
    }
}