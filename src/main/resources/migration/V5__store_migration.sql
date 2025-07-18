CREATE TYPE radius AS ENUM ('SMALL', 'MEDIUM', 'LARGE');

CREATE TYPE detail AS ENUM ('LOW', 'MEDIUM', 'HIGH');

CREATE TYPE alignment AS ENUM ('LEFT', 'CENTER', 'RIGHT');

CREATE TABLE store (
    id UUID PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    slug VARCHAR(200) NOT NULL UNIQUE,
    image VARCHAR(200) NOT NULL,
    brief VARCHAR(200) NOT NULL,
    description VARCHAR(200) NOT NULL,
    color_primary VARCHAR(100) NOT NULL,
    color_accent VARCHAR(100) NOT NULL,
    color_neutral VARCHAR(100) NOT NULL,
    color_dark VARCHAR(100) NOT NULL,
    verified BOOLEAN NOT NULL,
    radius radius NOT NULL,
    detail detail NOT NULL,
    alignment alignment NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
