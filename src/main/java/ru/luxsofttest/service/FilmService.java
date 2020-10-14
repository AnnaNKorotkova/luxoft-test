package ru.luxsofttest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.luxsofttest.exception.NotFoundException;
import ru.luxsofttest.model.Film;
import ru.luxsofttest.model.Genre;
import ru.luxsofttest.repository.FilmRepository;

import java.util.Set;

@Service
@Slf4j
@Transactional(readOnly = true)
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Film get(int filmId) {
        Film film1 = filmRepository.findById(filmId);
        if (film1 == null) {
            throw new NotFoundException();
        }
        log.info("save filmId {}", filmId);
        return film1;
    }


    @Transactional
    public Film saveOrUpdate(Film film) {
        Assert.notNull(film, "film id must not be null");
        Film saveFilm = filmRepository.save(film);
        log.info("save film {}", film);
        return saveFilm;
    }

    @Transactional
    public void delete(Film film) {
        Assert.notNull(film, "menuItem id must not be null");
        if (filmRepository.findById(film.id()) == null) {
            throw new NotFoundException();
        }
        filmRepository.delete(film);
        log.info("delete film {}", film);
    }


    public Page<Film> findAll() {
        log.info("findAll film");
        return filmRepository.findAll(PageRequest.of(0, 10, Sort.by("name")));
    }

    public Page<Film> findAllByNameSortByName(String name) {
        Assert.notNull(name, "name id must not be null");
        log.info("findAllByName : {}", name);
        return filmRepository.findFilmByNameIsContainingIgnoreCase(name, PageRequest.of(0, 10, Sort.by("name")));
    }

    public Page<Film> findAllByDirectorSortByName(String name) {
        Assert.notNull(name, "name id must not be null");
        log.info("findFilmByDirector : {}", name);
        return filmRepository.findFilmByDirector_NameIsContainingIgnoreCase(name, PageRequest.of(0, 10, Sort.by("name")));
    }

    public Page<Film> findAllByGenreSortByName(Genre genre) {
        Assert.notNull(genre, "name id must not be null");
        return filmRepository.findFilmByGenres(Set.of(genre), PageRequest.of(0, 10, Sort.by("name")));
    }
}
