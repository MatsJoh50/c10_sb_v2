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
    latitude    DECIMAL(10, 7) NOT NULL -- Adjusted precision for geographic coordinates
);
#                                          CONSTRAINT fk_user FOREIGN KEY (userId) REFERENCES users(id) ON DELETE SET NULL  -- Optional, if referencing a users table

#     dateOfCreation DATETIME DEFAULT CURRENT_TIMESTAMP,
#     dateOfChange DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
#     location POINT NOT NULL,
#     SPATIAL INDEX (location)
