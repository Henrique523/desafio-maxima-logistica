CREATE TABLE IF NOT EXISTS logistica.client(
  "id" SERIAL PRIMARY KEY,

  "nome" VARCHAR(255) NOT NULL,
  "cnpj" VARCHAR(50) NOT NULL,

  "address_id" BIGINT NOT NULL,

  "criado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "atualizado_em" TIMESTAMP,
  "deletado_em" TIMESTAMP,

  CONSTRAINT FK_address_id FOREIGN KEY(address_id) REFERENCES logistica.address(id)
);