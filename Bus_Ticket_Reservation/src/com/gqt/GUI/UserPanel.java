package com.gqt.GUI;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserPanel extends JFrame {
    public UserPanel() {
        setTitle("User Panel");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField dateField = new JTextField(10);
        JTextField busIdField = new JTextField(5);
        JTextField seatCountField = new JTextField(5);

        JButton viewBusesBtn = new JButton("View Buses");
        JButton reserveBtn = new JButton("Reserve Ticket");

        JTextArea output = new JTextArea(10, 40);
        output.setEditable(false);

        viewBusesBtn.addActionListener(e -> {
            String date = dateField.getText();
            List<Bus> buses = BusData.getBusesByDate(date);
            StringBuilder sb = new StringBuilder();
            for (Bus b : buses) sb.append(b).append("\n\n");
            output.setText(sb.isEmpty() ? "No buses available on this date." : sb.toString());
        });

        reserveBtn.addActionListener(e -> {
            int busId = Integer.parseInt(busIdField.getText());
            int count = Integer.parseInt(seatCountField.getText());
            Bus bus = BusData.getBusById(busId);
            if (bus != null && bus.hasSeats(count)) {
                String seatNos = bus.reserveSeats(count);
                output.setText("✅ Ticket Booked!\nBus: " + bus + "\nSeats: " + seatNos);
            } else {
                output.setText("❌ Bus not found or insufficient seats.");
            }
        });

        JPanel topPanel = new JPanel(new GridLayout(4, 2));
        topPanel.add(new JLabel("Enter Date:")); topPanel.add(dateField);
        topPanel.add(new JLabel("Bus ID:")); topPanel.add(busIdField);
        topPanel.add(new JLabel("Seats to Book:")); topPanel.add(seatCountField);
        topPanel.add(viewBusesBtn); topPanel.add(reserveBtn);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(output), BorderLayout.CENTER);
        setVisible(true);
    }
}
