package service;

import entity.Booking;
import java.util.List;

public interface BookingService {
    boolean bookSeat(Booking booking);
    boolean bookSeat(String username, int filmId, String seat, double payment);
    boolean checkSeatAvailability(int filmId, String seat);
    boolean checkSeatAvailability(Booking booking);
    List<Booking> getUserBookings(String username);
    List<Booking> getFilmBookings(int filmId);
    List<String> getBookedSeatsByFilm(int filmId);
}
