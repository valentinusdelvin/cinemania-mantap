package dao;

import entity.Booking;
import java.util.List;

public interface BookingDAO {
    boolean insertBooking(Booking booking);
    boolean isSeatAvailable(int filmId, String seat);
    List<Booking> getBookingsByUser(String username);
    List<Booking> getBookingsByFilm(int filmId);
}
