package br.com.gustavo.espacoKids.service.usuarioService;

import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.CadastroUsuarioDTO;
import br.com.gustavo.espacoKids.domain.entity.usuario.Usuario;
import br.com.gustavo.espacoKids.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encriptador;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado: " + login));
    }

    public Usuario cadastrar(CadastroUsuarioDTO dto) {
        if (repository.existsByLogin(dto.login())) {
            throw new IllegalArgumentException("Login já cadastrado: " + dto.login());
        }

        var usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setLogin(dto.login());
        usuario.setSenhaHash(encriptador.encode(dto.senha())); // encode aqui
        usuario.setPerfil(dto.perfil());
        usuario.setAtivo(true);

        return repository.save(usuario);
    }
}