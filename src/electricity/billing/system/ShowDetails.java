//package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ShowDetails extends JFrame implements ActionListener {
    JTextField meterField;
    JButton show, cancel;
    JTextArea detailsArea;

    public ShowDetails() {
        setSize(400, 400);
        setLocationRelativeTo(null);
        setTitle("Show Customer Details");
        setLayout(null);

        JLabel meterLabel = new JLabel("Meter Number:");
        meterLabel.setBounds(30, 30, 100, 30);
        add(meterLabel);

        meterField = new JTextField();
        meterField.setBounds(150, 30, 200, 30);
        add(meterField);

        show = new JButton("Show");
        show.setBounds(50, 80, 120, 30);
        show.addActionListener(this);
        add(show);

        cancel = new JButton("Cancel");
        cancel.setBounds(200, 80, 120, 30);
        cancel.addActionListener(this);
        add(cancel);

        detailsArea = new JTextArea();
        detailsArea.setBounds(30, 130, 320, 200);
        detailsArea.setEditable(false);
        add(detailsArea);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == show) {
            String meter = meterField.getText();

            try (Connection conn = DbConnection.getConnection()) {
                String sql = "SELECT * FROM emp WHERE meterNumber = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, meter);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("----- Customer Details -----\n");
                    sb.append("Name: ").append(rs.getString("name")).append("\n");
                    sb.append("Meter Number: ").append(rs.getString("meterNumber")).append("\n");
                    sb.append("Address: ").append(rs.getString("address")).append("\n");
                    sb.append("State: ").append(rs.getString("state")).append("\n");
                    sb.append("City: ").append(rs.getString("city")).append("\n");
                    sb.append("Email: ").append(rs.getString("email")).append("\n");
                    sb.append("Phone: ").append(rs.getString("phone")).append("\n");
                    sb.append("----------------------------");
                    detailsArea.setText(sb.toString());
                } else {
                    detailsArea.setText("No customer found for Meter Number: " + meter);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                detailsArea.setText("Error fetching details.");
            }
        } else if (e.getSource() == cancel) {
            dispose();
        }
    }
}
