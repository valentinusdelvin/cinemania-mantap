package service;

import entity.Booking;
import java.util.List;

public interface BookingService {
    boolean bookSeat(Booking booking);
    boolean checkSeatAvailability(int filmId, String seat);
    List<Booking> getUserBookings(String username);
    List<Booking> getFilmBookings(int filmId);
    List<String> getBookedSeatsByFilm(int filmId);
}
