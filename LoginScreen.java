package vt;
import javax.swing.*;
public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    public LoginScreen() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        add(panel);
        setVisible(true);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (authenticate(username, password)) {
                MainScreen mainScreen = new MainScreen();
                dispose();
            } else {
                JOptionPane.showMessageDialog(LoginScreen.this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    private boolean authenticate(String username, String password) 
        return username.equals("vaishthanu") && password.equals("123456");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginScreen();
        });
    }
}
