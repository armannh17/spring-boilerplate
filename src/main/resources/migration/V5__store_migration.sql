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
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
