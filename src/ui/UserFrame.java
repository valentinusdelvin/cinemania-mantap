package ui;

import entity.Booking;
import entity.Film;
import service.BookingService;
import service.FilmService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setSize(400, 300);
        setLayout(null);

        JButton beliFilmBtn = new JButton("Beli Film & Booking Kursi");
        beliFilmBtn.setBounds(80, 30, 240, 40);
        add(beliFilmBtn);

        JButton liatHistoryBtn = new JButton("Lihat History Pembelian");
        liatHistoryBtn.setBounds(80, 90, 240, 40);
        add(liatHistoryBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(80, 150, 240, 40);
        add(logoutBtn);

        beliFilmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    String kursi = JOptionPane.showInputDialog("Pilih Kursi (misal A1, B2):");
                    if (kursi != null) {
                        if (bookingService.checkSeatAvailability(film.getId(), kursi)) {
                            int bayar = JOptionPane.showConfirmDialog(null,
                                    "Harga: " + film.getPrice() + "\nBayar sekarang?", "Pembayaran",
                                    JOptionPane.YES_NO_OPTION);
                            if (bayar == JOptionPane.YES_OPTION) {
                                Booking booking = new Booking(0, username, film.getId(), kursi, film.getPrice());
                                if (bookingService.bookSeat(booking)) {
                                    JOptionPane.showMessageDialog(null, "Pembayaran sukses! Kursi berhasil dipesan.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Gagal booking.");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Kursi sudah di-booking!");
                        }
                    }
                }
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
                sb.append("Film: ").append(film.getTitle()).append(" | Kursi: ")
                        .append(b.getSeat()).append("\n");
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
