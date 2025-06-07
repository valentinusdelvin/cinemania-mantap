package service;

import dao.BookingDAO;
import entity.Booking;
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

    @Override
    public boolean checkSeatAvailability(int filmId, String seat) {
        return bookingDAO.isSeatAvailable(filmId, seat);
    }

    @Override
    public List<Booking> getUserBookings(String username) {
        return bookingDAO.getBookingsByUser(username);
    }

    @Override
    public List<Booking> getFilmBookings(int filmId) {
        return bookingDAO.getBookingsByFilm(filmId);
    }
}
