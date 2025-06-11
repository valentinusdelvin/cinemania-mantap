package service;

import dao.BookingDAO;
import entity.Booking;
import java.util.ArrayList;
import java.util.List;

public class BookingServiceImpl implements BookingService {
    private BookingDAO bookingDAO;

    public BookingServiceImpl(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    @Override
    public boolean bookSeat(Booking booking) {
        if (bookingDAO.isSeatAvailable(booking.getFilmId(), booking.getSeat())) {
            return bookingDAO.insertBooking(booking);
        }
        return false;
    }

    public boolean bookSeat(String username, int filmId, String seat, double payment) {
        Booking booking = new Booking(0, username, filmId, seat, payment);
        return bookSeat(booking);  // tetap panggil method utama
    }

    @Override
    public boolean checkSeatAvailability(int filmId, String seat) {
        return bookingDAO.isSeatAvailable(filmId, seat);
    }

    public boolean checkSeatAvailability(Booking booking) {
        return checkSeatAvailability(booking.getFilmId(), booking.getSeat());
    }

    @Override
    public List<Booking> getUserBookings(String username) {
        return bookingDAO.getBookingsByUser(username);
    }

    @Override
    public List<Booking> getFilmBookings(int filmId) {
        return bookingDAO.getBookingsByFilm(filmId);
    }

    @Override
    public List<String> getBookedSeatsByFilm(int filmId) {
        List<Booking> bookings = bookingDAO.getBookingsByFilm(filmId);
        List<String> seats = new ArrayList<>();
        for (Booking booking : bookings) {
            seats.add(booking.getSeat());
        }
        return seats;
    }
}
