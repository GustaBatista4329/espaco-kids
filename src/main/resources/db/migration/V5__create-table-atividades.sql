CREATE TABLE atividades (
    id                    BIGSERIAL       PRIMARY KEY,
    aluno_id              BIGINT          NOT NULL,
    titulo                VARCHAR(255)    NOT NULL,
    descricao             TEXT,
    arquivo_url           VARCHAR(500)    NOT NULL,
    data_disponibilizacao DATE            NOT NULL,
    criado_em             TIMESTAMP       NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_atividades_aluno
        FOREIGN KEY (aluno_id) REFERENCES alunos(id)
);