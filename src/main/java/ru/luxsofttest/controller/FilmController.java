package ru.luxsofttest.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.luxsofttest.model.Film;
import ru.luxsofttest.model.Genre;
import ru.luxsofttest.service.FilmService;

@RestController
@RequestMapping(produces  = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping("/sort/{name}")
    public Page<Film> findAllByDirectorSortByName(@PathVariable String name) {
        return filmService.findAllByDirectorSortByName(name);
    }


    @GetMapping("/sort/{name}")
    public Page<Film> findAllByGenreSortByName(@PathVariable String name) {
        return filmService.findAllByGenreSortByName(Genre.valueOf(name));
    }
}
