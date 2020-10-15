package ru.luxsofttest.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.luxsofttest.model.Film;
import ru.luxsofttest.model.Genre;
import ru.luxsofttest.service.FilmService;

@RestController("/test")
public class FilmController  {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping(value = "/findAll")
    public Page<Film> findAll() {
        return filmService.findAll();
    }

    @PostMapping(value = "/")
    public Film saveOrUpdate(Film film) {
        return filmService.saveOrUpdate(film);
    }

    @PostMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        Film film = filmService.get(id);
        filmService.delete(film);
    }

    @GetMapping(value = "/sort/{name}")
    public Page<Film> findAllByNameSortByName(@PathVariable String name) {
        return filmService.findAllByNameSortByName(name);
    }

    @GetMapping("/sort/director/{name}")
    public Page<Film> findAllByDirectorSortByName(@PathVariable String name) {
        return filmService.findAllByDirectorSortByName(name);
    }

    @GetMapping("/sort/genre/{name}")
    public Page<Film> findAllByGenreSortByName(@PathVariable String name) {
        return filmService.findAllByGenreSortByName(Genre.valueOf(name));
    }
}
