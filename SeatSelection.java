package vt;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
public class SeatSelection extends JFrame {
    private List<JCheckBox> seatCheckboxes; // List to hold seat checkboxes
    private JButton confirmButton;
    private JButton cancelButton;
    public SeatSelection() {
        setTitle("Seat Selection");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 10));
        seatCheckboxes = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            JCheckBox seatCheckbox = new JCheckBox("Seat " + i);
            seatCheckboxes.add(seatCheckbox);
            panel.add(seatCheckbox);
        }
        confirmButton = new JButton("Confirm Seats");
        panel.add(confirmButton);
        cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        add(panel);
        setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> selectedSeats = getSelectedSeats();
                if (selectedSeats.isEmpty()) {
                    JOptionPane.showMessageDialog(SeatSelection.this,
                            "Please select at least one seat.",
                            "Seat Selection Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    PaymentScreen paymentScreen = new PaymentScreen(selectedSeats);
                    paymentScreen.setVisible(true); 
                    dispose();
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
    private List<String> getSelectedSeats() {
        List<String> selectedSeats = new ArrayList<>();
for (JCheckBox checkbox : seatCheckboxes) {
            if (checkbox.isSelected()) {
                selectedSeats.add(checkbox.getText());
            }
        }
        return selectedSeats;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SeatSelection();
        });
    }
}
