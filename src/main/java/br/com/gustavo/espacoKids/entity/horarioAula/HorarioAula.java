package br.com.gustavo.espacoKids.entity.horarioAula;

import br.com.gustavo.espacoKids.entity.aluno.Aluno;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "horarios_aula")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class HorarioAula {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFim;

    @Column(nullable = false)
    private Boolean ativo = true;

}
