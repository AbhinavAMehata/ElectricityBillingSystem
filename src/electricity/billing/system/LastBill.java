//package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LastBill extends JFrame implements ActionListener {
    JTextField meterField;
    JButton show, cancel;
    JTextArea billArea;

    public LastBill() {
        setSize(400, 350);
        setLocationRelativeTo(null);
        setTitle("Last Bill");
        setLayout(null);

        JLabel meterLabel = new JLabel("Meter Number:");
        meterLabel.setBounds(30, 30, 100, 30);
        add(meterLabel);

        meterField = new JTextField();
        meterField.setBounds(150, 30, 200, 30);
        add(meterField);

        show = new JButton("Show Last Bill");
        show.setBounds(50, 80, 140, 30);
        show.addActionListener(this);
        add(show);

        cancel = new JButton("Cancel");
        cancel.setBounds(210, 80, 140, 30);
        cancel.addActionListener(this);
        add(cancel);

        billArea = new JTextArea();
        billArea.setBounds(30, 130, 320, 170);
        billArea.setEditable(false);
        add(billArea);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == show) {
            String meter = meterField.getText();

            try (Connection conn = DbConnection.getConnection()) {
                String sql = "SELECT * FROM bill WHERE meterNumber = ? ORDER BY id DESC LIMIT 1";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, meter);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("----- Last Bill -----\n");
                    sb.append("Meter Number: ").append(rs.getString("meterNumber")).append("\n");
                    sb.append("Units Consumed: ").append(rs.getInt("units")).append("\n");
                    sb.append("Month: ").append(rs.getString("month")).append("\n");
                    sb.append("Amount: ").append(rs.getDouble("amount")).append("\n");
                    sb.append("---------------------");
                    billArea.setText(sb.toString());
                } else {
                    billArea.setText("No bill found for Meter Number: " + meter);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                billArea.setText("Error fetching last bill.");
            }
        } else if (e.getSource() == cancel) {
            dispose();
        }
    }
}
