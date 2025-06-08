
# 🎥 Cinemania - Simple Cinema Booking System

## 📑 Description
Cinemania is a simple desktop-based Cinema Booking System application built using **Java**, **SQLite**, and **Swing GUI**. The system allows users to register bookings for films, while administrators can manage the film list and view booking data.

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

## 🗂️ Project Structure

      src/
      ├── dao/               // Data Access Object (FilmDAO, BookingDAO, UserDAO, AdminDAO)
      ├── entity/            // Model class (Film, Booking)
      ├── infrastructure/    // DatabaseManager
      ├── service/           // FilmService, BookingService
      ├── ui/                // JFrame to login, admin, and user
      ├── util/              // DatabaseUtil to setup initial database
      └── Main.java          // Application entry point

## 🗄️ Database Structure

- **films**
  - `id` INTEGER PRIMARY KEY AUTOINCREMENT
  - `title` TEXT NOT NULL
  - `description` TEXT
  - `price` REAL NOT NULL

- **bookings**
  - `id` INTEGER PRIMARY KEY AUTOINCREMENT
  - `film_id` INTEGER NOT NULL
  - `username` TEXT NOT NULL
  - `seat` TEXT NOT NULL
  - `payment` REAL NOT NULL

- **users**
  - `id` INTEGER PRIMARY KEY AUTOINCREMENT
  - `username` TEXT NOT NULL UNIQUE
  - `password` TEXT NOT NULL

- **admins**
  - `id` INTEGER PRIMARY KEY AUTOINCREMENT
  - `username` TEXT NOT NULL UNIQUE
  - `password` TEXT NOT NULL

## 🛠️ Technologies Used
- **Java**: Programming language for application development.
- **SQLite**: Lightweight database for storing film and booking data.
- **JDBC**: Java Database Connectivity for database operations.
- **Swing**: GUI toolkit for building the user interface.

## 📝 How to Run

1. Make sure you have **JDK 17+** and **SQLite JDBC** library installed.
2. Clone the project into your favorite Java IDE (IntelliJ, Eclipse, etc.).
3. Run the `Main` class to start the application.

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
