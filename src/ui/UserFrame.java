package ui;

import entity.Booking;
import entity.Film;
import service.BookingService;
import service.FilmService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class UserFrame extends JFrame {
    private String username;
    private FilmService filmService;
    private BookingService bookingService;

    public UserFrame(String username, FilmService filmService, BookingService bookingService) {
        this.username = username;
        this.filmService = filmService;
        this.bookingService = bookingService;

        setTitle("User Menu - " + username);
        setSize(400, 400);
        setLayout(null);

        JButton liatDeskripsiBtn = new JButton("Lihat Deskripsi Film");
        liatDeskripsiBtn.setBounds(80, 90, 240, 40);
        add(liatDeskripsiBtn);

        JButton beliFilmBtn = new JButton("Beli Film & Booking Kursi");
        beliFilmBtn.setBounds(80, 30, 240, 40);
        add(beliFilmBtn);

        JButton liatHistoryBtn = new JButton("Lihat History Pembelian");
        liatHistoryBtn.setBounds(80, 150, 240, 40);
        add(liatHistoryBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(80, 210, 240, 40);
        add(logoutBtn);

        beliFilmBtn.addActionListener(e -> {
            List<Film> films = filmService.getAllFilms();
            if (films.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Belum ada film tersedia.");
                return;
            }

            String[] filmTitles = films.stream().map(Film::getTitle).toArray(String[]::new);
            String selected = (String) JOptionPane.showInputDialog(null, "Pilih Film", "Film",
                    JOptionPane.QUESTION_MESSAGE, null, filmTitles, filmTitles[0]);

            Film film = films.stream().filter(f -> f.getTitle().equals(selected)).findFirst().orElse(null);
            if (film != null) {
                new SeatSelectionFrame(username, film.getId(), (int) film.getPrice(), bookingService);
            }
        });

        liatDeskripsiBtn.addActionListener(e -> {
            List<Film> films = filmService.getAllFilms();
            if (films.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Belum ada film tersedia.");
                return;
            }

            String[] filmTitles = films.stream().map(Film::getTitle).toArray(String[]::new);
            String selected = (String) JOptionPane.showInputDialog(null, "Pilih Film", "Film",
                    JOptionPane.QUESTION_MESSAGE, null, filmTitles, filmTitles[0]);

            Film film = films.stream().filter(f -> f.getTitle().equals(selected)).findFirst().orElse(null);
            if (film != null) {
                JOptionPane.showMessageDialog(null, film.getDescription(), "Deskripsi " + film.getTitle(),
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        liatHistoryBtn.addActionListener(e -> {
            List<Booking> bookings = bookingService.getUserBookings(username);
            if (bookings.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Belum ada history.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (Booking b : bookings) {
                Film film = filmService.getFilmById(b.getFilmId());
                sb.append("Film: ").append(film.getTitle())
                        .append(" | Kursi: ").append(b.getSeat())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        });

        logoutBtn.addActionListener(e -> {
            new LoginFrame(filmService, bookingService);
            dispose();
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
