//package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginScreen extends JFrame implements ActionListener {
    JButton login, cancel;
    JTextField username;
    JPasswordField password;

    public LoginScreen() {
        setSize(400, 300);
        setLocationRelativeTo(null);
        setTitle("Login");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);
        add(userLabel);

        username = new JTextField();
        username.setBounds(150, 50, 150, 30);
        add(username);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30);
        add(passLabel);

        password = new JPasswordField();
        password.setBounds(150, 100, 150, 30);
        add(password);

        login = new JButton("Login");
        login.setBounds(80, 160, 100, 30);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(200, 160, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String user = username.getText();
            String pass = new String(password.getPassword());

            try (Connection conn = DbConnection.getConnection()) {
                String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, user);
                pst.setString(2, pass);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    // Login success
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    dispose();
                    new MainSystem();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error!");
            }
        } else if (e.getSource() == cancel) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}
