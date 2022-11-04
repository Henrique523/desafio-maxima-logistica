CREATE TABLE IF NOT EXISTS logistica.address(
    "id" SERIAL PRIMARY KEY,

    "latitude" DECIMAL(10, 8) NOT NULL,
    "longitude" DECIMAL(10, 8) NOT NULL,

    "criado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "atualizado_em" TIMESTAMP,
    "deletado_em" TIMESTAMP
);