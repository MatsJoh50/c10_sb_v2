TRUNCATE category;

INSERT INTO category(name, symbol, description)
VALUES ('heart', '❤️', 'ett vackert hjärta'),
       ('star',  '★', 'en fin diamant'),
       ('trophy', '🏆', 'En trophe');


TRUNCATE locations;
-- Inserting famous locations into Locations table
INSERT INTO locations (name, kategori, user_id, is_private, description, coordinates)
VALUES
    ( 'Eiffel Tower', 1, 'martin', TRUE, 'Famous landmark in Paris',
     ST_GeomFromText('POINT(48.858370 2.294481 )', 4326)),
    ( 'Statue of Liberty', 2, 'erik', FALSE, 'Iconic statue in New York',
     ST_GeomFromText('POINT(40.689247 -74.044500 )', 4326)),
    ( 'Great Wall of China', 3, 'alex', FALSE, 'Historic wall in China',
     ST_GeomFromText('POINT(40.431908 116.570374 )', 4326)),
    ( 'Sydney Opera House', 1, 'jogge', TRUE, 'Opera house in Sydney',
     ST_GeomFromText('POINT(-33.856159 151.215256 )', 4326));

