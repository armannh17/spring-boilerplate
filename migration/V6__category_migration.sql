CREATE TABLE category (
    id UUID PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    image VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    store_id UUID NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_category_store FOREIGN KEY (store_id) REFERENCES store(id) ON DELETE RESTRICT
);

CREATE TABLE field (
    id UUID PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    category_id UUID NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_field_category FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE RESTRICT
);
