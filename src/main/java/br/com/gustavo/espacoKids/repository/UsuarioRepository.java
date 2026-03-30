package br.com.gustavo.espacoKids.repository;

import br.com.gustavo.espacoKids.domain.entity.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
