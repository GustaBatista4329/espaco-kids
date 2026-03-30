package br.com.gustavo.espacoKids.domain.entity.horarioAula;

import br.com.gustavo.espacoKids.domain.dto.horarioAulaDTO.CadastroHorarioAulaDTO;
import br.com.gustavo.espacoKids.domain.entity.aluno.Aluno;
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

    public HorarioAula(Aluno aluno, CadastroHorarioAulaDTO cadastroHorarioAulaDTO) {

        this.aluno = aluno;
        this.diaSemana = cadastroHorarioAulaDTO.diaSemana();
        this.horaInicio = cadastroHorarioAulaDTO.horaInicio();
        this.horaFim = cadastroHorarioAulaDTO.horaFim();

    }
}
