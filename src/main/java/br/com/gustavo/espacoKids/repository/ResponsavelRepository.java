package br.com.gustavo.espacoKids.repository;

import br.com.gustavo.espacoKids.domain.entity.responsavel.Responsavel;
import br.com.gustavo.espacoKids.domain.entity.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.perfil = 'RESPONSAVEL' AND u.responsavel IS NULL AND u.ativo = true")
    List<Usuario> findUsuariosResponsaveisSemVinculo();
}
