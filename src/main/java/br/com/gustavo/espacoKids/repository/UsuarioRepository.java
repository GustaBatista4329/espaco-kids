package br.com.gustavo.espacoKids.repository;

import br.com.gustavo.espacoKids.domain.entity.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);

    boolean existsByLogin(String login);

    @Query("SELECT u FROM Usuario u WHERE u.perfil = 'RESPONSAVEL' AND u.responsavel IS NULL AND u.ativo = true")
    List<Usuario> findUsuariosResponsaveisSemVinculo();
}
