package br.com.gustavo.espacoKids.domain.entity.usuario;

import br.com.gustavo.espacoKids.domain.dto.usuarioDTO.CadastroUsuarioDTO;
import br.com.gustavo.espacoKids.domain.entity.responsavel.Responsavel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "usuarioId", cascade = CascadeType.ALL)
    private Responsavel responsavel;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senhaHash;

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "criado_em", nullable = false, insertable = false, updatable = false)
    private LocalDateTime criadoEm;

    public Usuario(CadastroUsuarioDTO cadastroUsuarioDTO) {
        this.nome = cadastroUsuarioDTO.nome();
        this.email = cadastroUsuarioDTO.email();
        this.senhaHash = cadastroUsuarioDTO.senha_hash();
        this.perfil = cadastroUsuarioDTO.perfil();
    }
}
