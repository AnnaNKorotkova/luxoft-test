DELETE FROM film_genres;
DELETE FROM director;
DELETE FROM film;

ALTER SEQUENCE global_seq RESTART WITH 100000;

insert into director (name) values
    ('Земекис'),                                        -- 100000
    ('Кэмерон'),                                        -- 100001
    ('Ларс Фон Триер');                                 -- 100002

insert into film (name, description, director_id) values
    ('Назад в Будущее 1', 'экшен', 100000),             -- 100003
    ('Назад в Будущее 2', 'экшен', 100000),             -- 100004
    ('Назад в Будущее 3', 'экшен', 100000),             -- 100005
    ('Титаник', 'Драма', 100001),                       -- 100006
    ('Терминатор 1', 'Производственная драма', 100001), -- 100007
    ('Терминатор 2', 'комедия', 100001),                -- 100008
    ('Догвиль', 'Басня', 100002);                       -- 100009


insert into film_genres (film_id, genre) values
(100003, 'COMEDY'),                                     -- 100010
(100004, 'COMEDY'),                                     -- 100011
(100005, 'COMEDY'),                                     -- 100012
(100006, 'DRAMA'),                                      -- 100013
(100007, 'THRILLER'),                                   -- 100014
(100008, 'THRILLER'),                                   -- 100015
(100009, 'FANTASY');                                    -- 100016
