package service;

import dao.FilmDAO;
import entity.Film;
import java.util.List;

public class FilmServiceImpl implements FilmService {
    private FilmDAO filmDAO;

    public FilmServiceImpl(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @Override
    public boolean addFilm(Film film) {
        return filmDAO.insertFilm(film);
    }

    @Override
    public List<Film> getAllFilms() {
        return filmDAO.getAllFilms();
    }

    @Override
    public Film getFilmById(int id) {
        return filmDAO.getFilmById(id);
    }
}
