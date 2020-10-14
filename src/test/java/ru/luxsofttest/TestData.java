package ru.luxsofttest;

import ru.luxsofttest.model.Director;
import ru.luxsofttest.model.Film;
import ru.luxsofttest.model.Genre;

import java.util.List;


public class TestData {
    public static TestMatcher<Film> FILM_TO_MATCHER = TestMatcher.usingFieldsComparator(Film.class);

    public static final Director DIR_1 = new Director(100000,"Земекис");
    public static final Director DIR_2 = new Director(100001,"Кэмерон");
    public static final Director DIR_3 = new Director(100002,"Ларс Фон Триер");

    public static final Film FILM_1 = new Film(100003,"Назад в Будущее 1",DIR_1,"экшен", List.of(Genre.COMEDY));
    public static final Film FILM_2 = new Film(100004,"Назад в Будущее 2",DIR_1,"экшен",List.of(Genre.COMEDY));
    public static final Film FILM_3= new Film(100005,"Назад в Будущее 3",DIR_1,"экшен",List.of(Genre.COMEDY));
    public static final Film FILM_4= new Film(100006,"Титаник",DIR_2,"Драма",List.of(Genre.DRAMA));
    public static final Film FILM_5= new Film(100007,"Терминатор 1",DIR_2,"Производственная драма",List.of(Genre.THRILLER));
    public static final Film FILM_6= new Film(100008,"Терминатор 2",DIR_2,"комедия",List.of(Genre.THRILLER));
    public static final Film FILM_7 = new Film(100009,"Догвиль",DIR_3,"Басня",List.of(Genre.FANTASY));

   public static Film getNew(){
       return new  Film(100010,"Защитники", DIR_3,"суперпупер", List.of(Genre.COMEDY));

   }

}
