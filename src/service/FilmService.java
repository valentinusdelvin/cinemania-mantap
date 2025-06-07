package service;

import entity.Film;
import java.util.List;

public interface FilmService {
    boolean addFilm(Film film);
    List<Film> getAllFilms();
    Film getFilmById(int id);
}
