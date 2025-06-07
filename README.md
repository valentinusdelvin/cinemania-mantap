
# 🎥 Cinema Booking System — Java + SQLite + Swing

## 📑 Description
This is a simple desktop-based Cinema Booking System application built using **Java**, **SQLite**, and **Swing GUI**. The system allows users to register bookings for films, while administrators can manage the film list and view booking data.

## 📦 Features
- **Login system** for Admin and User roles.
- **Admin:**
  - Add new films.
  - View bookings for each film.
- **User:**
  - View available films.
  - See film descriptions.
  - Book available seats.
  - View personal booking history.
- SQLite database integration for persistent data.

## 🗄️ Database Structure

      src/
      ├── dao/               // Data Access Object (FilmDAO, BookingDAO, UserDAO, AdminDAO)
      ├── entity/            // Model class (Film, Booking)
      ├── infrastructure/    // DatabaseManager
      ├── service/           // FilmService, BookingService
      ├── ui/                // JFrame to login, admin, and user
      ├── util/              // DatabaseUtil to setup initial database
      └── Main.java          // Application entry point

## 📝 How to Run

1. Make sure you have **JDK 17+** installed.
2. Import the project into your favorite Java IDE (IntelliJ, Eclipse, etc.).
3. Run `DatabaseUtil.setupDatabase()` once to create the required tables and dummy accounts.
4. Run the `Main` class to start the application.

## 🔑 Default Accounts

**Admins:**
- Username: `admin_123` | Password: `tesAdmin`
- Username: `admin_anjay` | Password: `tesAdmin`

**Users:**
- Username: `user_123` | Password: `tesUser`
- Username: `pemlan_123` | Password: `tesUser`

## 📸 Screenshots (optional)
*Coming Soon*

## 👨‍💻 Author

- Name: Valentinus Delvin Wicaksono
- NIM: 245150700111016
