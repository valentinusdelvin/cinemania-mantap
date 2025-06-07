package ui;

import entity.Film;
import service.BookingService;
import service.FilmService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminFrame extends JFrame {
    private String username;
    private FilmService filmService;
    private BookingService bookingService;

    public AdminFrame(String username, FilmService filmService, BookingService bookingService) {
        this.username = username;
        this.filmService = filmService;
        this.bookingService = bookingService;

        setTitle("Admin Menu");
        setSize(400, 300);
        setLayout(null);

        JButton addFilmBtn = new JButton("Tambah Film");
        addFilmBtn.setBounds(100, 30, 200, 40);
        add(addFilmBtn);

        JButton lihatKursiBtn = new JButton("Lihat Kursi Terbooking");
        lihatKursiBtn.setBounds(100, 90, 200, 40);
        add(lihatKursiBtn);

        JButton masukUserMenuBtn = new JButton("Masuk Menu User");
        masukUserMenuBtn.setBounds(100, 150, 200, 40);
        add(masukUserMenuBtn);

        addFilmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String judul = JOptionPane.showInputDialog("Judul:");
                String desc = JOptionPane.showInputDialog("Deskripsi:");
                double harga = Double.parseDouble(JOptionPane.showInputDialog("Harga:"));
                filmService.addFilm(new Film(0, judul, desc, harga));
                JOptionPane.showMessageDialog(null, "Film berhasil ditambahkan.");
            }
        });

        lihatKursiBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Film> films = filmService.getAllFilms();
                String[] filmTitles = films.stream().map(Film::getTitle).toArray(String[]::new);
                String selected = (String) JOptionPane.showInputDialog(null, "Pilih Film", "Film",
                        JOptionPane.QUESTION_MESSAGE, null, filmTitles, filmTitles[0]);
                Film film = films.stream().filter(f -> f.getTitle().equals(selected)).findFirst().orElse(null);
                if (film != null) {
                    var bookings = bookingService.getFilmBookings(film.getId());
                    StringBuilder sb = new StringBuilder();
                    for (var b : bookings) {
                        sb.append("Kursi: ").append(b.getSeat()).append(" - ").append(b.getUsername()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "Belum ada kursi dibooking.");
                }
            }
        });

        masukUserMenuBtn.addActionListener(e -> {
            new UserFrame(username, filmService, bookingService);
            dispose();
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
