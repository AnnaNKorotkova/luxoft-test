package ru.luxsofttest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "director")
@NoArgsConstructor
public class Director extends AbstractNamedEntity {

    public Director(Integer id, String name, List<Film> films) {
        super(id, name);
        this.films = films;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "director")
    private List<Film> films;

    public Director(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Director{" +
                "name='" + name + '\'' +
                '}';
    }
}
