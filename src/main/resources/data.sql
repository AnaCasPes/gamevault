-- data.sql se ejecuta en cada arranque: TRUNCATE con RESTART IDENTITY borra
-- las filas Y reinicia el contador de id a 1 — un DELETE normal no reinicia
-- ese contador, así que los estudio_id de más abajo (1-5) podrían no existir
-- de verdad si ya habías creado algún estudio antes, y el INSERT siguiente
-- fallaría por clave foránea
TRUNCATE TABLE videojuego, estudio RESTART IDENTITY CASCADE;

INSERT INTO estudio (nombre, pais) VALUES
    ('Supergiant Games', 'Estados Unidos'),
    ('Team Cherry', 'Australia'),
    ('Motion Twin', 'Francia'),
    ('Toby Fox', 'Estados Unidos'),
    ('Klei Entertainment', 'Canadá');

INSERT INTO videojuego (titulo, precio, fecha_lanzamiento, estudio_id) VALUES
    ('Hades', 24.99, '2020-09-17', 1),
    ('Pyre', 19.99, '2017-07-25', 1),
    ('Transistor', 19.99, '2014-05-20', 1),
    ('Bastion', 14.99, '2011-07-20', 1),
    ('Hades II', 29.99, '2024-05-06', 1),
    ('Hollow Knight', 14.99, '2017-02-24', 2),
    ('Hollow Knight: Silksong', 19.99, '2025-09-04', 2),
    ('Dead Cells', 24.99, '2018-08-07', 3),
    ('Undertale', 9.99, '2015-09-15', 4),
    ('Mark of the Ninja', 14.99, '2012-09-07', 5),
    ('Don''t Starve', 14.99, '2013-04-23', 5),
    ('Oxygen Not Included', 24.99, '2019-07-30', 5);