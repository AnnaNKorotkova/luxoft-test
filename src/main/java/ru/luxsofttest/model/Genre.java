package ru.luxsofttest.model;

import com.sun.istack.Nullable;

public enum Genre {
    COMEDY("Комедия"),
    THRILLER("Триллер"),
    DRAMA("Драма"),
    FANTASY("Фэнтази");

    private String id;

    Genre(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }
    @Nullable
    public static Genre fromId(String id) {
        for (Genre g : Genre.values()) {
            if (g.getId().equals(id)) {
                return g;
            }
        }
        return null;
    }
}
