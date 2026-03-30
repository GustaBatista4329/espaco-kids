package br.com.gustavo.espacoKids.domain.entity.aluno;

import br.com.gustavo.espacoKids.domain.dto.alunoDTO.CadastroAlunoDTO;
import br.com.gustavo.espacoKids.domain.entity.atividade.Atividade;
import br.com.gustavo.espacoKids.domain.entity.horarioAula.HorarioAula;
import br.com.gustavo.espacoKids.domain.entity.responsavel.Responsavel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alunos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsavel_id")
    private Responsavel responsavel;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String serie;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "criado_em", nullable = false, insertable = false, updatable = false)
    private LocalDateTime criadoEm;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HorarioAula> horarioAulas = new ArrayList<>();

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Atividade>  atividades= new ArrayList<>();

    public Aluno(CadastroAlunoDTO cadastroAlunoDTO, Responsavel responsavel) {
        this.responsavel = responsavel;
        this.nome = cadastroAlunoDTO.nome();
        this.dataNascimento = cadastroAlunoDTO.dataNascimento();
        this.serie = cadastroAlunoDTO.serie();
    }

    //metodos
    public void adicionarHorario(HorarioAula horario) {
        this.horarioAulas.add(horario);
        horario.setAluno(this);
    }

    public void adicionarAtividade(Atividade atividade) {
        this.atividades.add(atividade);
        atividade.setAluno(this);
    }
}
