package entity;

public class Booking {
    private int id;
    private String username;
    private int filmId;
    private String seat;
    private double payment;

    public Booking(int id, String username, int filmId, String seat, double payment) {
        this.id = id;
        this.username = username;
        this.filmId = filmId;
        this.seat = seat;
        this.payment = payment;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public int getFilmId() { return filmId; }
    public String getSeat() { return seat; }
    public double getPayment() { return payment; }
}
