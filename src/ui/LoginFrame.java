package ui;

import dao.AdminDAO;
import dao.AdminDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import service.BookingService;
import service.FilmService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserDAO userDAO;
    private AdminDAO adminDAO;

    public LoginFrame(FilmService filmService, BookingService bookingService) {
        this.userDAO = new UserDAOImpl();
        this.adminDAO = new AdminDAOImpl();

        setTitle("Login");
        setSize(300, 200);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(20, 20, 80, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(110, 20, 150, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(20, 60, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(110, 60, 150, 25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(90, 110, 100, 30);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname = usernameField.getText();
                String pass = new String(passwordField.getPassword());

                if (adminDAO.login(uname, pass)) {
                    JOptionPane.showMessageDialog(null, "Login sukses!");
                    new AdminFrame(uname, filmService, bookingService);
                    dispose();
                } else if (userDAO.login(uname, pass)) {
                    JOptionPane.showMessageDialog(null, "Login sukses!");
                    new UserFrame(uname, filmService, bookingService);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Username atau password salah!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
