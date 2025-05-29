package com.gqt.GUI;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JFrame {
    public AdminPanel() {
        setTitle("Admin Panel");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField busId = new JTextField(5);
        JTextField source = new JTextField(10);
        JTextField destination = new JTextField(10);
        JTextField date = new JTextField(10);
        JTextField dep = new JTextField(10);
        JTextField arr = new JTextField(10);
        JTextField reach = new JTextField(10);
        JTextField location = new JTextField(10);

        JButton addBtn = new JButton("Add Bus");
        JButton viewBtn = new JButton("View All Buses");

        JTextArea output = new JTextArea(10, 40);
        output.setEditable(false);

        addBtn.addActionListener(e -> {
            Bus b = new Bus(
                Integer.parseInt(busId.getText()),
                source.getText(),
                destination.getText(),
                date.getText(),
                dep.getText(),
                arr.getText(),
                reach.getText(),
                location.getText()
            );
            BusData.addBus(b);
            output.setText("✅ Bus Added!\n" + b);
        });

        viewBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Bus b : BusData.buses) sb.append(b).append("\n\n");
            output.setText(sb.toString());
        });

        JPanel inputPanel = new JPanel(new GridLayout(9, 2));
        inputPanel.add(new JLabel("Bus ID:")); inputPanel.add(busId);
        inputPanel.add(new JLabel("Source:")); inputPanel.add(source);
        inputPanel.add(new JLabel("Destination:")); inputPanel.add(destination);
        inputPanel.add(new JLabel("Date:")); inputPanel.add(date);
        inputPanel.add(new JLabel("Departure Time:")); inputPanel.add(dep);
        inputPanel.add(new JLabel("Arrival Time:")); inputPanel.add(arr);
        inputPanel.add(new JLabel("Reaching Time:")); inputPanel.add(reach);
        inputPanel.add(new JLabel("Current Location:")); inputPanel.add(location);
        inputPanel.add(addBtn); inputPanel.add(viewBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(output), BorderLayout.CENTER);
        setVisible(true);
    }
}
