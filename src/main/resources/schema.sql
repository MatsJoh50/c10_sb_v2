DROP TABLE IF EXISTS kategori;
CREATE TABLE IF NOT EXISTS category
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        varchar(255),
    symbol      varchar(255) CHARACTER SET utf8mb4 NOT NULL,
    description varchar(255)
);

DROP TABLE IF EXISTS locations;
CREATE TABLE IF NOT EXISTS locations
(
    id          INT            PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255)   NOT NULL,
    kategori    INT            NOT NULL,
    user_id     VARCHAR(255)   NOT NULL DEFAULT 0,
    is_private  BOOLEAN        NOT NULL DEFAULT TRUE,
    description TEXT,
    coordinates GEOMETRY       NOT NULL SRID 4326,
    date_of_creation TIMESTAMP   DEFAULT NOW(),
    date_of_change   TIMESTAMP   DEFAULT NOW() ON UPDATE NOW(),
    deleted     BOOLEAN        NOT NULL DEFAULT FALSE,
    deleted_by  VARCHAR(255)
);
