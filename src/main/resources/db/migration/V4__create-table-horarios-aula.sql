CREATE TABLE horarios_aula (
    id           BIGSERIAL       PRIMARY KEY,
    aluno_id     BIGINT          NOT NULL,
    dia_semana   VARCHAR(20)     NOT NULL,
    hora_inicio  TIME            NOT NULL,
    hora_fim     TIME            NOT NULL,
    ativo        BOOLEAN         NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_horarios_aula_aluno
        FOREIGN KEY (aluno_id) REFERENCES alunos(id)
);