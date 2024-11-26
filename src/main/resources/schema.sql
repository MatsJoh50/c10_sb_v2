DROP TABLE IF EXISTS kategori;
CREATE TABLE IF NOT EXISTS kategori
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        varchar(255),
    symbol      varchar(255) CHARACTER SET utf8mb4 NOT NULL,
    description varchar(255)
);

DROP TABLE IF EXISTS locations;
CREATE TABLE IF NOT EXISTS locations
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255)   NOT NULL,
    kategori    INT            NOT NULL,
    userId      INT            NOT NULL DEFAULT 0,
    is_private  BOOLEAN        NOT NULL DEFAULT TRUE,
    description TEXT,
    longitude   DECIMAL(10, 7) NOT NULL, -- Adjusted precision for geographic coordinates
    latitude    DECIMAL(10, 7) NOT NULL, -- Adjusted precision for geographic coordinates
    deleted     BOOLEAN        NOT NULL DEFAULT FALSE,
    deleted_by  INT
);
#     dateOfChange   TIMESTAMP   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
#     dateOfCreation TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
