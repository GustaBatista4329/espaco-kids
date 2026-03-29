package br.com.gustavo.espacoKids.entity.responsavel;

import br.com.gustavo.espacoKids.entity.aluno.Aluno;
import br.com.gustavo.espacoKids.entity.usuario.Usuario;
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

}
