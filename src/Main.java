import dao.BookingDAOImpl;
import dao.FilmDAOImpl;
import service.BookingService;
import service.BookingServiceImpl;
import service.FilmService;
import service.FilmServiceImpl;
import ui.LoginFrame;
import util.DatabaseUtil;

public class Main {
    public static void main(String[] args) {
        DatabaseUtil.setupDatabase();
        FilmService filmService = new FilmServiceImpl(new FilmDAOImpl());
        BookingService bookingService = new BookingServiceImpl(new BookingDAOImpl());

        new LoginFrame(filmService, bookingService);
    }
}
