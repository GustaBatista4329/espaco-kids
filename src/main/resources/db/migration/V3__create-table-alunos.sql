CREATE TABLE alunos (
    id               BIGSERIAL       PRIMARY KEY,
    responsavel_id   BIGINT          NOT NULL,
    nome             VARCHAR(255)    NOT NULL,
    data_nascimento  DATE            NOT NULL,
    serie            VARCHAR(50)     NOT NULL,
    ativo            BOOLEAN         NOT NULL DEFAULT TRUE,
    criado_em        TIMESTAMP       NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_alunos_responsavel
        FOREIGN KEY (responsavel_id) REFERENCES responsaveis(id)
);