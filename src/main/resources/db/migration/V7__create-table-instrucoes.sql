CREATE TABLE instrucoes(
    id BIGINT AUTO_INCREMENT NOT NULL,
    alunos_id BIGINT NOT NULL,
    instrutores_id BIGINT NOT NULL,
    data_hora DATETIME NOT NULL,

    PRIMARY KEY(id),

    CONSTRAINT fk_instrucoes_alunos_id FOREIGN KEY alunos(alunos_id) REFERENCES alunos(id),
    CONSTRAINT fk_instrucoes_instutores_id FOREIGN KEY instrutores(instrutores_id) REFERENCES instrutores(id)
);