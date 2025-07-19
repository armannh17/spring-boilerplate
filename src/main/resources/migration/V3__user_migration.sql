CREATE TYPE role AS ENUM ('USER', 'OWNER', 'ADMIN');

CREATE TABLE "user" (
  id             UUID PRIMARY KEY,
  phone          VARCHAR(200) NOT NULL UNIQUE,
  locked         BOOLEAN NOT NULL,
  role           role NOT NULL,
  credential_id  UUID NOT NULL UNIQUE,
  created_at     TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at     TIMESTAMPTZ NOT NULL DEFAULT now(),

  CONSTRAINT fk_user_credential FOREIGN KEY (credential_id)
    REFERENCES credential(id) ON DELETE RESTRICT
);
