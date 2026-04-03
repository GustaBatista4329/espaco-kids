package br.com.gustavo.espacoKids.domain.entity.atividade;

import br.com.gustavo.espacoKids.domain.entity.aluno.Aluno;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "atividades_aluno")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class AtividadeAluno {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banco_atividade_id", nullable = false)
    private BancoAtividade bancoAtividade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @Column(name = "data_atribuicao", nullable = false, insertable = false, updatable = false)
    private LocalDateTime dataAtribuicao;

    private String enunciado;
}
