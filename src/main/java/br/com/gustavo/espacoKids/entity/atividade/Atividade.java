package br.com.gustavo.espacoKids.entity.atividade;

import br.com.gustavo.espacoKids.entity.aluno.Aluno;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "atividades")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Atividade {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @Column(nullable = false)
    private String arquivoUrl;

    @Column(nullable = false)
    private LocalDate dataDisponibilizacao;

    @Column(name = "criado_em", nullable = false, insertable = false, updatable = false)
    private LocalDateTime criadoEm;

}
