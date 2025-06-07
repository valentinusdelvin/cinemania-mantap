import dao.BookingDAOImpl;
import dao.FilmDAOImpl;
import service.BookingServiceImpl;
import service.FilmServiceImpl;
import ui.LoginFrame;
import util.DatabaseUtil;

public class Main {
    public static void main(String[] args) {
        DatabaseUtil.setupDatabase();
        var filmService = new FilmServiceImpl(new FilmDAOImpl());
        var bookingService = new BookingServiceImpl(new BookingDAOImpl());

        new LoginFrame(filmService, bookingService);
    }
}
