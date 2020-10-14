package ru.luxsofttest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "film")
@NoArgsConstructor
public class Film extends AbstractNamedEntity{

    public Film(Integer id, String name, Director director, String description, List<Genre> genres) {
        super(id, name);
        this.director = director;
        this.description = description;
        this.genres = genres;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @Column(name = "description")
    private String description;

    @CollectionTable(name = "film_genres", joinColumns = @JoinColumn(name = "film_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"film_id", "genre"}, name = "film_genres_unique_idx")})
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Genre> genres;



    @Override
    public String toString() {
        return "Film{" +
                "director=" + director +
                ", description='" + description + '\'' +
                ", genres=" + genres +
                ", name='" + name + '\'' +
                '}';
    }
}
