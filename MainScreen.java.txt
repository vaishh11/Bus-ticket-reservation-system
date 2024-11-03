package vt;
import javax.swing.*;
public class MainScreen extends JFrame {
    public MainScreen() {
        setTitle("Bus Ticket Booking System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
         JPanel panel = new JPanel();
        JButton seatSelectionButton = new JButton("Select Seats");
        JButton paymentButton = new JButton("Proceed to Payment");
        JButton logoutButton = new JButton("Logout");      panel.add(seatSelectionButton);
        panel.add(paymentButton);
        panel.add(logoutButton);
        add(panel);
        setVisible(true);
        seatSelectionButton.addActionListener(e -> {
            SeatSelection seatSelection = new SeatSelection();
            dispose();
        });
        paymentButton.addActionListener(e -> {
            PaymentScreen paymentScreen = new PaymentScreen(null);
            dispose();
        });
        logoutButton.addActionListener(e -> {
            LoginScreen loginScreen = new LoginScreen();
            dispose();
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainScreen();
        });
    }
}
