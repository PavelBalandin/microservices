----------------------------------------------------------------
-- INVENTORY
----------------------------------------------------------------
CREATE TABLE inventory
(
    id       BIGSERIAL NOT NULL PRIMARY KEY,
    sku_code BIGINT    NOT NULL,
    quantity BIGINT    NOT NULL
);