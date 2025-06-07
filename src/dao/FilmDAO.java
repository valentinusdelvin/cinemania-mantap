package dao;

import entity.Film;
import java.util.List;

public interface FilmDAO {
    boolean insertFilm(Film film);
    List<Film> getAllFilms();
    Film getFilmById(int id);
}
