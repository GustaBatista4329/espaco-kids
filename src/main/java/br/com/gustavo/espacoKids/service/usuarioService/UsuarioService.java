package br.com.gustavo.espacoKids.service.usuarioService;

import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.AtualizarSenhaDTO;
import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.AtualizarStatusDTO;
import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.AtualizarUsuarioDTO;
import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.CadastroUsuarioDTO;
import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.UsuarioDetalhesDTO;
import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.UsuarioSelectDTO;
import br.com.gustavo.espacoKids.domain.entity.usuario.Perfil;
import br.com.gustavo.espacoKids.domain.entity.usuario.Usuario;
import br.com.gustavo.espacoKids.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    public void atualizarSenha(AtualizarSenhaDTO dto) {
        var usuario = repository.findById(dto.usuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        usuario.setSenhaHash(encriptador.encode(dto.novaSenha()));
        repository.save(usuario);
    }

    public List<UsuarioSelectDTO> listarUsuariosParaSenha() {
        return repository.findByAtivoTrueAndPerfilNot(Perfil.ADM).stream()
                .map(UsuarioSelectDTO::new)
                .toList();
    }

    public List<UsuarioDetalhesDTO> listarTodosUsuarios() {
        return repository.findAllByOrderByNomeAsc().stream()
                .map(UsuarioDetalhesDTO::new)
                .toList();
    }

    public UsuarioDetalhesDTO atualizarUsuario(Long id, AtualizarUsuarioDTO dto) {
        var usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        if (dto.nome() != null) usuario.setNome(dto.nome());

        if (dto.login() != null) {
            if (repository.existsByLoginAndIdNot(dto.login(), id)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Login já está em uso");
            }
            usuario.setLogin(dto.login());
        }

        if (dto.perfil() != null) usuario.setPerfil(dto.perfil());

        return new UsuarioDetalhesDTO(repository.save(usuario));
    }

    public void atualizarStatus(Long id, AtualizarStatusDTO dto) {
        var usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        usuario.setAtivo(dto.ativo());
        repository.save(usuario);
    }
}