package ru.luxsofttest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.luxsofttest.TestData;
import ru.luxsofttest.exception.NotFoundException;
import ru.luxsofttest.model.Film;
import ru.luxsofttest.model.Genre;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ru.luxsofttest.TestData.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@Sql(scripts = "classpath:/data.sql")
class FilmServiceTest {
    @Autowired
    private FilmService filmService;

    @Test
    void save() {
        Film newFilm = getNew();
        filmService.saveOrUpdate(newFilm);
        FILM_TO_MATCHER.assertMatch(newFilm, filmService.get(newFilm.id()));
    }
    @Test
    void update() {
        Film forUpdate = FILM_1;
        forUpdate.setGenres(List.of(Genre.FANTASY));
        filmService.saveOrUpdate(forUpdate);
        FILM_TO_MATCHER.assertMatch(forUpdate, filmService.get(forUpdate.id()));
    }

    @Test
    void delete() {
        filmService.delete(FILM_1);
        Assertions.assertThrows(NotFoundException.class,
                () -> filmService.get(FILM_1.id()));
    }

    @Test
    void findAll() {
        List<Film> films = new ArrayList<>(List.of(FILM_7, FILM_1, FILM_2, FILM_3, FILM_5, FILM_6, FILM_4));
        films.sort(Comparator.comparing(Film::getName));
        FILM_TO_MATCHER.assertMatch(films, filmService.findAll().toList());
    }

    @Test
    void findAllByNameSortByName() {
        Page<Film> allByNameSortByName = filmService.findAllByNameSortByName("тита");
        List<Film> collect = allByNameSortByName.toList();
        FILM_TO_MATCHER.assertMatch(List.of(TestData.FILM_4), collect);
    }

    @Test
    void findAllByDirectorSortByName() {
        List<Film> fon = filmService.findAllByDirectorSortByName("фон").toList();
        FILM_TO_MATCHER.assertMatch(List.of(TestData.FILM_7), fon);
    }

    @Test
    void findAllByGenreSortByName() {
        List<Film> collect = filmService.findAllByGenreSortByName(Genre.DRAMA).toList();
        FILM_TO_MATCHER.assertMatch(List.of(TestData.FILM_4), collect);
    }
}