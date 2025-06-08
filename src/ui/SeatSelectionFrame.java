package ui;

import entity.Booking;
import service.BookingService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SeatSelectionFrame extends JFrame {
    private String username;
    private int filmId;
    private int price;
    private BookingService bookingService;

    public SeatSelectionFrame(String username, int filmId, int price, BookingService bookingService) {
        this.username = username;
        this.filmId = filmId;
        this.price = price;
        this.bookingService = bookingService;

        setTitle("Pilih Kursi - Film ID: " + filmId);
        setSize(600, 700);
        setLayout(new BorderLayout());

        JPanel seatPanel = new JPanel(new GridLayout(5, 7, 5, 5));  // 5 baris, 7 kolom (6 kursi + 1 spasi)
        String[][] seats = {
                {"A1", "A2", "A3", "", "A4", "A5", "A6"},
                {"B1", "B2", "B3", "", "B4", "B5", "B6"},
                {"C1", "C2", "C3", "", "C4", "C5", "C6"},
                {"D1", "D2", "D3", "", "D4", "D5", "D6"},
                {"E1", "E2", "E3", "", "E4", "E5", "E6"}
        };

        for (String[] row : seats) {
            for (String seat : row) {
                if (seat.equals("")) {
                    seatPanel.add(new JLabel(""));  // spasi kosong (jalur)
                } else {
                    JButton seatBtn = new JButton(seat);
                    if (!bookingService.checkSeatAvailability(filmId, seat)) {
                        seatBtn.setEnabled(false);
                        seatBtn.setBackground(Color.RED);
                    } else {
                        seatBtn.setBackground(Color.GREEN);
                    }

                    seatBtn.addActionListener((ActionEvent e) -> {
                        int confirm = JOptionPane.showConfirmDialog(null,
                                "Harga: " + price + "\nBooking kursi " + seatBtn.getText() + "?", "Konfirmasi",
                                JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            Booking booking = new Booking(0, username, filmId, seatBtn.getText(), price);
                            if (bookingService.bookSeat(booking)) {
                                JOptionPane.showMessageDialog(null, "Booking sukses!");
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Gagal booking.");
                            }
                        }
                    });

                    seatPanel.add(seatBtn);
                }
            }
        }

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(500, 50));
        bottomPanel.add(spacer, BorderLayout.NORTH);

        JLabel screenLabel = new JLabel("LAYAR", SwingConstants.CENTER);
        screenLabel.setFont(new Font("Arial", Font.BOLD, 18));
        screenLabel.setForeground(Color.BLUE);
        bottomPanel.add(screenLabel, BorderLayout.CENTER);

        add(seatPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
