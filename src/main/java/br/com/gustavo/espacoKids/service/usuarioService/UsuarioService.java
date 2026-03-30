package br.com.gustavo.espacoKids.service.usuarioService;

import br.com.gustavo.espacoKids.dto.usuarioDTO.CadastroUsuarioDTO;
import br.com.gustavo.espacoKids.entity.usuario.Perfil;
import br.com.gustavo.espacoKids.entity.usuario.Usuario;
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
        usuario.setSenha_hash(encriptador.encode(usuario.getSenha_hash()));
        repository.save(usuario);

        return cadastroUsuarioDTO;
    }
}