----------------------------------------------------------------
-- PRODUCTS
----------------------------------------------------------------
CREATE TABLE products
(
    id          BIGSERIAL    NOT NULL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    price       NUMERIC(19, 2)       NOT NULL
);