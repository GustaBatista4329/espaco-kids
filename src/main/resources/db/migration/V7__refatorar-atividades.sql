-- Remover tabela antiga
DROP TABLE IF EXISTS atividades;

-- Banco de atividades (catálogo de PDFs reutilizáveis)
CREATE TABLE banco_atividades (
    id              BIGSERIAL       PRIMARY KEY,
    titulo          VARCHAR(255)    NOT NULL,
    descricao       TEXT,
    nome_arquivo    VARCHAR(500)    NOT NULL UNIQUE,
    criado_em       TIMESTAMP       NOT NULL DEFAULT NOW()
);

-- Vínculo atividade <-> aluno (com expiração)
CREATE TABLE atividades_aluno (
    id                  BIGSERIAL   PRIMARY KEY,
    banco_atividade_id  BIGINT      NOT NULL,
    aluno_id            BIGINT      NOT NULL,
    data_atribuicao     TIMESTAMP   NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_atividade_banco FOREIGN KEY (banco_atividade_id) REFERENCES banco_atividades(id),
    CONSTRAINT fk_atividade_aluno FOREIGN KEY (aluno_id) REFERENCES alunos(id)
);
