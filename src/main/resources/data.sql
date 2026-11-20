TRUNCATE TABLE videojuego, estudio RESTART IDENTITY CASCADE;

INSERT INTO estudio (nombre, pais) VALUES
                                       ('Supergiant Games', 'Estados Unidos'),
                                       ('Team Cherry', 'Australia'),
                                       ('Motion Twin', 'Francia'),
                                       ('Toby Fox', 'Estados Unidos'),
                                       ('Klei Entertainment', 'Canadá'),
                                       ('ConcernedApe', 'Estados Unidos'),
                                       ('Innersloth', 'Estados Unidos');

INSERT INTO videojuego (titulo, precio, fecha_lanzamiento, estudio_id, detalles_plataforma) VALUES
    ('Hades', 24.99, '2020-09-17', 1, '{"steam": {"idApp": 1145360}, "switch": {"idApp": "HDS01"}}'::jsonb),
    ('Pyre', 19.99, '2017-07-25', 1, '{"steam": {"idApp": 462770}}'::jsonb),
    ('Transistor', 19.99, '2014-05-20', 1, '{"steam": {"idApp": 237930}, "switch": {"idApp": "TRS01"}}'::jsonb),
    ('Bastion', 14.99, '2011-07-20', 1, '{"steam": {"idApp": 107100}}'::jsonb),
    ('Hades II', 29.99, '2024-05-06', 1, '{"steam": {"idApp": 1145350}}'::jsonb),
    ('Hollow Knight', 14.99, '2017-02-24', 2, '{"steam": {"idApp": 367520}, "switch": {"idApp": "HK001"}}'::jsonb),
    ('Hollow Knight: Silksong', 19.99, '2025-09-04', 2, '{"steam": {"idApp": 1030300}, "switch": {"idApp": "HKS01"}}'::jsonb),
    ('Dead Cells', 24.99, '2018-08-07', 3, '{"steam": {"idApp": 588650}, "switch": {"idApp": "DC001"}}'::jsonb),
    ('Undertale', 9.99, '2015-09-15', 4, '{"steam": {"idApp": 391540}}'::jsonb),
    ('Deltarune', 0.00, '2018-10-31', 4, '{"steam": {"idApp": 1671210}, "switch": {"idApp": "DTR01"}}'::jsonb),
    ('Mark of the Ninja', 14.99, '2012-09-07', 5, '{"steam": {"idApp": 214560}}'::jsonb),
    ('Don''t Starve', 14.99, '2013-04-23', 5, '{"steam": {"idApp": 219740}, "switch": {"idApp": "DS001"}}'::jsonb),
    ('Don''t Starve Together', 14.99, '2016-04-21', 5, '{"steam": {"idApp": 322330}, "switch": {"idApp": "DST01"}}'::jsonb),
    ('Oxygen Not Included', 24.99, '2019-07-30', 5, '{"steam": {"idApp": 457140}}'::jsonb),
    ('Invisible, Inc.', 19.99, '2015-05-12', 5, '{"steam": {"idApp": 243970}}'::jsonb),
    ('Griftlands', 24.99, '2021-10-12', 5, '{"steam": {"idApp": 601840}, "switch": {"idApp": "GRF01"}}'::jsonb),
    ('Stardew Valley', 13.99, '2016-02-26', 6, '{"steam": {"idApp": 413150}, "switch": {"idApp": "STV01"}}'::jsonb),
    ('Among Us', 4.99, '2018-06-15', 7, '{"steam": {"idApp": 945360}, "switch": {"idApp": "AU001"}}'::jsonb);