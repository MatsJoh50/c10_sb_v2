TRUNCATE kategori;

INSERT INTO kategori(name, symbol, description)
VALUES ('heart', '❤️', 'ett vackert hjärta'),
       ('star',  '★', 'en fin diamant'),
       ('trophy', '🏆', 'En trophe');


TRUNCATE locations;
-- Inserting famous locations into Locations table

# INSERT INTO locations (name, kategori, userId, isPrivate, description, longitude, latitude)
# VALUES
#     ('Eiffel Tower', 1, 101, FALSE, 'Famous landmark in Paris', 2.294481, 48.858370),
#     ('Statue of Liberty', 2, 102, FALSE, 'Iconic statue in New York', -74.044500, 40.689247),
#     ('Great Wall of China', 3, 103, FALSE, 'Historic wall in China', 116.570374, 40.431908),
#     ('Sydney Opera House', 1, 104, FALSE, 'Opera house in Sydney', 151.215256, -33.856159);

INSERT INTO locations (name)
VALUES
    ('Eiffel Tower'),
    ('Statue of Liberty'),
    ('Great Wall of China'),
    ('Sydney Opera House')
