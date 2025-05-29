package com.gqt.GUI;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Bus Ticket Reservation");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton adminBtn = new JButton("Admin Login");
        JButton userBtn = new JButton("User");

        adminBtn.addActionListener(e -> {
            dispose();
            new AdminPanel();
        });

        userBtn.addActionListener(e -> {
            dispose();
            new UserPanel();
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.add(new JLabel("Select Role:", SwingConstants.CENTER));
        panel.add(adminBtn);
        panel.add(userBtn);
        add(panel);
        setVisible(true);
    }
}


