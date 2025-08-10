//package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddCustomer extends JFrame implements ActionListener {
    JTextField nameField, meterField, addressField, stateField, cityField, emailField, phoneField;
    JButton submit, cancel;

    public AddCustomer() {
        setSize(400, 450);
        setLocationRelativeTo(null);
        setTitle("Add Customer");
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 100, 30);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(150, 30, 200, 30);
        add(nameField);

        JLabel meterLabel = new JLabel("Meter Number:");
        meterLabel.setBounds(30, 70, 100, 30);
        add(meterLabel);
        meterField = new JTextField();
        meterField.setBounds(150, 70, 200, 30);
        add(meterField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(30, 110, 100, 30);
        add(addressLabel);
        addressField = new JTextField();
        addressField.setBounds(150, 110, 200, 30);
        add(addressField);

        JLabel stateLabel = new JLabel("State:");
        stateLabel.setBounds(30, 150, 100, 30);
        add(stateLabel);
        stateField = new JTextField();
        stateField.setBounds(150, 150, 200, 30);
        add(stateField);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setBounds(30, 190, 100, 30);
        add(cityLabel);
        cityField = new JTextField();
        cityField.setBounds(150, 190, 200, 30);
        add(cityField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 230, 100, 30);
        add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(150, 230, 200, 30);
        add(emailField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(30, 270, 100, 30);
        add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBounds(150, 270, 200, 30);
        add(phoneField);

        submit = new JButton("Submit");
        submit.setBounds(70, 330, 100, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(200, 330, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String name = nameField.getText();
            String meter = meterField.getText();
            String address = addressField.getText();
            String state = stateField.getText();
            String city = cityField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();

            try (Connection conn = DbConnection.getConnection()) {
                String sql = "INSERT INTO emp (name, meterNumber, address, state, city, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, meter);
                pst.setString(3, address);
                pst.setString(4, state);
                pst.setString(5, city);
                pst.setString(6, email);
                pst.setString(7, phone);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Customer added successfully!");
                dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to add customer.");
            }
        } else if (e.getSource() == cancel) {
            dispose();
        }
    }
}

