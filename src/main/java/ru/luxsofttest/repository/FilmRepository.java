package ru.luxsofttest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.luxsofttest.model.Film;
import ru.luxsofttest.model.Genre;

import java.util.Set;

@Repository
@Transactional
public interface FilmRepository extends PagingAndSortingRepository<Film, Integer> {

    @EntityGraph(attributePaths = {"director"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<Film> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"director"}, type = EntityGraph.EntityGraphType.LOAD)
    Film findById(int id);

    @EntityGraph(attributePaths = {"director"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<Film> findFilmByNameIsContainingIgnoreCase(String name, Pageable pageable);

    @EntityGraph(attributePaths = {"director"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<Film> findFilmByDirector_NameIsContainingIgnoreCase(String name, Pageable pageable);

    @EntityGraph(attributePaths = {"director"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select f from Film f join f.genres g where g in ?1")
    Page<Film> findFilmByGenres(Set<Genre> names, Pageable pageable);
}
