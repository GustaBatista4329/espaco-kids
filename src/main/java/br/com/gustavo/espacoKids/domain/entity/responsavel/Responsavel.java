package br.com.gustavo.espacoKids.domain.entity.responsavel;

import br.com.gustavo.espacoKids.domain.dto.responsavelDTO.CadastroResponsavelDTO;
import br.com.gustavo.espacoKids.domain.entity.aluno.Aluno;
import br.com.gustavo.espacoKids.domain.entity.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "responsaveis")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Responsavel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuarioId;

    private String telefone;

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Aluno> alunos = new ArrayList<>();

    public Responsavel(CadastroResponsavelDTO cadastroResponsavelDTO, Usuario usuario) {
        this.usuarioId = usuario;
        this.telefone = cadastroResponsavelDTO.telefone();
    }
}
