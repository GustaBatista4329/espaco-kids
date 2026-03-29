CREATE TABLE usuarios (
    id          BIGSERIAL       PRIMARY KEY,
    nome        VARCHAR(255)    NOT NULL,
    email       VARCHAR(255)    NOT NULL UNIQUE,
    senha_hash  VARCHAR(255)    NOT NULL,
    perfil      VARCHAR(50)     NOT NULL,
    ativo       BOOLEAN         NOT NULL DEFAULT TRUE,
    criado_em   TIMESTAMP       NOT NULL DEFAULT NOW()
);