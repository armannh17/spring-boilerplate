CREATE TABLE credential (
  id           UUID PRIMARY KEY,
  otp_code     VARCHAR(100),
  otp_expiry   TIMESTAMPTZ,
  created_at   TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at   TIMESTAMPTZ NOT NULL DEFAULT now()
);
