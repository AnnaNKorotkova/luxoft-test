DROP TABLE IF EXISTS film_genres;
DROP TABLE IF EXISTS director;
DROP TABLE IF EXISTS film;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE director
(
    id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name    VARCHAR NOT NULL
);

CREATE TABLE film
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name        VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    director_id INTEGER NOT NULL,
    FOREIGN KEY (director_id) REFERENCES director (id) ON DELETE CASCADE
);

CREATE TABLE film_genres
(
    film_id INTEGER NOT NULL,
    genre   VARCHAR,
    CONSTRAINT film_genres_idx UNIQUE (film_id, genre),
    FOREIGN KEY (film_id) REFERENCES film (id) ON DELETE CASCADE
);