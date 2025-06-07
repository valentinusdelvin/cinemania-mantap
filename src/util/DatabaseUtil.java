package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:sqlite:cinema.db";

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setupDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            if (conn == null) {
                System.out.println("Failed to connect to the database.");
                return;
            }

            // Buat tabel films
            String createFilmTable = """
                CREATE TABLE IF NOT EXISTS films (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    description TEXT,
                    price REAL NOT NULL
                );
            """;

            // Buat tabel bookings
            String createBookingTable = """
                CREATE TABLE IF NOT EXISTS bookings (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    film_id INTEGER NOT NULL,
                    username TEXT NOT NULL,
                    seat TEXT NOT NULL,
                    payment REAL NOT NULL,
                    FOREIGN KEY(film_id) REFERENCES films(id)
                );
            """;

            String createUserTable = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL
                );
            """;

            String createAdminTable = """
                CREATE TABLE IF NOT EXISTS admins (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL
                );
            """;

            stmt.execute(createFilmTable);
            stmt.execute(createBookingTable);
            stmt.execute(createUserTable);
            stmt.execute(createAdminTable);
            stmt.execute("INSERT OR IGNORE INTO users (username, password) VALUES ('user_123', 'tesUser'), ('pemlan_123', 'tesUser');");
            stmt.execute("INSERT OR IGNORE INTO admins (username, password) VALUES ('admin_123', 'tesAdmin'), ('admin_anjay', 'tesAdmin');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
