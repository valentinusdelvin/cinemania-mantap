package dao;

import entity.Booking;
import infrastructure.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {
    @Override
    public boolean insertBooking(Booking booking) {
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO bookings (username, film_id, seat, payment) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, booking.getUsername());
            stmt.setInt(2, booking.getFilmId());
            stmt.setString(3, booking.getSeat());
            stmt.setDouble(4, booking.getPayment());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isSeatAvailable(int filmId, String seat) {
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM bookings WHERE film_id=? AND seat=?")) {
            stmt.setInt(1, filmId);
            stmt.setString(2, seat);
            ResultSet rs = stmt.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Booking> getBookingsByUser(String username) {
        List<Booking> list = new ArrayList<>();
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM bookings WHERE username=?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Booking(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("film_id"),
                        rs.getString("seat"),
                        rs.getDouble("payment")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Booking> getBookingsByFilm(int filmId) {
        List<Booking> list = new ArrayList<>();
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM bookings WHERE film_id=?")) {
            stmt.setInt(1, filmId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Booking(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("film_id"),
                        rs.getString("seat"),
                        rs.getDouble("payment")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
