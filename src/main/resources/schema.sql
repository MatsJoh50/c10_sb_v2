DROP TABLE IF EXISTS kategori;
CREATE TABLE IF NOT EXISTS kategori
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    symbol varchar(255) CHARACTER SET utf8mb4 NOT NULL,
    description varchar(255)
);

DROP TABLE IF EXISTS locations;
CREATE TABLE IF NOT EXISTS locations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);
#     kategori INT NOT NULL,
#     userId INT NOT NULL,
#     isPrivate BOOLEAN DEFAULT TRUE,
#     description VARCHAR(255),
#     longitude DOUBLE NOT NULL,
#     latitude DOUBLE NOT NULL,
#     dateOfCreation DATETIME DEFAULT CURRENT_TIMESTAMP,
#     dateOfChange DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

#     location POINT NOT NULL,
#     SPATIAL INDEX (location)
