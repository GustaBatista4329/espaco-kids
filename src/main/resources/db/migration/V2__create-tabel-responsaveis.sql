CREATE TABLE responsaveis (
    id          BIGSERIAL       PRIMARY KEY,
    usuario_id  BIGINT          NOT NULL UNIQUE,
    telefone    VARCHAR(20),

    CONSTRAINT fk_responsaveis_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);