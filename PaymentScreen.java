package vt;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class PaymentScreen extends JFrame {
    private JLabel seatsLabel;
    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JButton payButton;
    private JButton cancelButton;
    private List<String> selectedSeats;
    public PaymentScreen(List<String> selectedSeats) {
        this.selectedSeats = selectedSeats;
        setTitle("Payment");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        seatsLabel = new JLabel("Selected Seats: " + selectedSeats.toString());
        panel.add(seatsLabel);
        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberField = new JTextField(20);
        panel.add(cardNumberLabel);
        panel.add(cardNumberField);
        JLabel expiryDateLabel = new JLabel("Expiry Date (MM/YYYY):");
        expiryDateField = new JTextField(7);
        panel.add(expiryDateLabel);
        panel.add(expiryDateField);
        payButton = new JButton("Pay");
        panel.add(payButton);
        cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        add(panel);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNumber = cardNumberField.getText();
                String expiryDate = expiryDateField.getText();
                if (cardNumber.isEmpty() || expiryDate.isEmpty()) {
                    JOptionPane.showMessageDialog(PaymentScreen.this,
                            "Please fill in all payment details.",
                            "Payment Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean paymentSuccessful = processPayment(cardNumber, expiryDate);
                    if (paymentSuccessful) {
                        showConfirmationPage(selectedSeats);
                        dispose(); // Close payment screen
                    } else {
                        JOptionPane.showMessageDialog(PaymentScreen.this,
                                "Payment failed. Please try again.",
                                "Payment Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    private boolean processPayment(String cardNumber, String expiryDate) {
        return !cardNumber.isEmpty() && !expiryDate.isEmpty();
    }
    private void showConfirmationPage(List<String> seats) {
        JFrame confirmationFrame = new JFrame("Ticket Confirmation");
        confirmationFrame.setSize(400, 300);
        confirmationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JTextArea confirmationTextArea = new JTextArea(10, 30);
        confirmationTextArea.setEditable(false);
        StringBuilder confirmationText = new StringBuilder();
        confirmationText.append("Thank you for booking!\n\n");
        confirmationText.append("Reserved Seats:\n");
        for (String seat : seats) {
            confirmationText.append(seat).append("\n");
        }
        confirmationTextArea.setText(confirmationText.toString());
        JScrollPane scrollPane = new JScrollPane(confirmationTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmationFrame.dispose();
            }
        });
        panel.add(closeButton, BorderLayout.SOUTH);
        confirmationFrame.add(panel);
        confirmationFrame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PaymentScreen(null);
        });
    }
}
