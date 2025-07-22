CREATE TYPE notification_type AS ENUM ('OTP_CODE');

CREATE TABLE notification (
  id       UUID PRIMARY KEY,
  template VARCHAR(100) NOT NULL,
  sender   VARCHAR(100) NOT NULL,
  type     notification_type NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
