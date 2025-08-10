//package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame implements Runnable {
    Thread thread;

    public SplashScreen() {
        setSize(600, 300);
        setLocationRelativeTo(null);
        setUndecorated(true);
        getContentPane().setBackground(Color.white);

        JLabel heading = new JLabel("Electricity Billing System");
        heading.setBounds(100, 20, 400, 40);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        heading.setForeground(Color.BLUE);
        add(heading);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(150, 200, 300, 20);
        progressBar.setStringPainted(true);
        add(progressBar);

        setLayout(null);
        setVisible(true);

        thread = new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(40);
                    progressBar.setValue(i);
                }
                dispose();
                new LoginScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }

    @Override
    public void run() {
        // Not used because thread is lambda
    }

    public static void main(String[] args) {
        new SplashScreen();
    }
}
