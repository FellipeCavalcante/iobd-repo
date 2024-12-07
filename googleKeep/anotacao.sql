DROP DATABASE IF EXISTS anotacao;

CREATE DATABASE anotacao;

\C anotacao;

-- CREATE TYPE cor_enum AS ENUM ('azul', 'vermelho', 'verde', 'amarelo', 'roxo', 'laranja', 'preto', 'branco');

CREATE TABLE anotacoes(
    id        SERIAL PRIMARY KEY,
    titulo    VARCHAR(255) NOT NULL,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descricao TEXT NOT NULL,
    cor       VARCHAR(20) NOT NULL,
    foto      BYTEA
);
