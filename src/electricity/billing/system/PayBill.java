//package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PayBill extends JFrame implements ActionListener {
    JTextField meterField, amountField;
    JButton pay, cancel;

    public PayBill() {
        setSize(350, 250);
        setLocationRelativeTo(null);
        setTitle("Pay Bill");
        setLayout(null);

        JLabel meterLabel = new JLabel("Meter Number:");
        meterLabel.setBounds(30, 40, 100, 30);
        add(meterLabel);

        meterField = new JTextField();
        meterField.setBounds(150, 40, 150, 30);
        add(meterField);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(30, 90, 100, 30);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 90, 150, 30);
        add(amountField);

        pay = new JButton("Pay");
        pay.setBounds(50, 150, 100, 30);
        pay.addActionListener(this);
        add(pay);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 150, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pay) {
            String meter = meterField.getText();
            String amountStr = amountField.getText();

            try (Connection conn = DbConnection.getConnection()) {
                // Add payment record to bill table - assumes 'bill' table has meterNumber, units, month, amount columns
                String sql = "INSERT INTO bill (meterNumber, units, month, amount) VALUES (?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, meter);
                pst.setInt(2, 0); // or prompt for units
                pst.setString(3, java.time.Month.from(java.time.LocalDate.now()).name());
                pst.setDouble(4, Double.parseDouble(amountStr));

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Bill paid successfully!");
                dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Payment failed.");
            }
        } else if (e.getSource() == cancel) {
            dispose();
        }
    }
}
