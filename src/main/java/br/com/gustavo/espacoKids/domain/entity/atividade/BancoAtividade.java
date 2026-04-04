package br.com.gustavo.espacoKids.domain.entity.atividade;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "banco_atividades")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class BancoAtividade {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @Column(nullable = false, unique = true)
    private String nomeArquivo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Categoria categoria = Categoria.GERAL;

    @Column(name = "criado_em", nullable = false, insertable = false, updatable = false)
    private LocalDateTime criadoEm;
}
