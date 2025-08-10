//package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainSystem extends JFrame implements ActionListener {
    JButton addCustomer, payBill, generateBill, showDetails, lastBill, logout;

    public MainSystem() {
        setSize(600, 400);
        setLocationRelativeTo(null);
        setTitle("Electricity Billing System - Main");
        setLayout(new GridLayout(3, 2, 10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addCustomer = new JButton("Add Customer");
        payBill = new JButton("Pay Bill");
        generateBill = new JButton("Generate Bill");
        showDetails = new JButton("Show Details");
        lastBill = new JButton("Last Bill");
        logout = new JButton("Logout");

        addCustomer.addActionListener(this);
        payBill.addActionListener(this);
        generateBill.addActionListener(this);
        showDetails.addActionListener(this);
        lastBill.addActionListener(this);
        logout.addActionListener(this);

        add(addCustomer);
        add(payBill);
        add(generateBill);
        add(showDetails);
        add(lastBill);
        add(logout);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCustomer) {
            new AddCustomer();
        } else if (e.getSource() == payBill) {
            new PayBill();
        } else if (e.getSource() == generateBill) {
            new GenerateBill();
        } else if (e.getSource() == showDetails) {
            new ShowDetails();
        } else if (e.getSource() == lastBill) {
            new LastBill();
        } else if (e.getSource() == logout) {
            dispose();
            new LoginScreen();
        }
    }

    public static void main(String[] args) {
        new MainSystem();
    }
}
