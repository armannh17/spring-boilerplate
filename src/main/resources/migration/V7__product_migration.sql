CREATE TABLE product (
    id UUID PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    image VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    published BOOLEAN NOT NULL,
    archived BOOLEAN NOT NULL,
    field_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),

    CONSTRAINT fk_product_field FOREIGN KEY (field_id) REFERENCES field(id) ON DELETE RESTRICT
);

CREATE TABLE variant (
    id UUID PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    overview VARCHAR(200) NOT NULL,
    product_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),

    CONSTRAINT fk_variant_product FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE RESTRICT
);

CREATE TABLE variety (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    color VARCHAR(100) NOT NULL,
    variant_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    CONSTRAINT fk_variety_variant FOREIGN KEY (variant_id) REFERENCES variant(id) ON DELETE RESTRICT
);
