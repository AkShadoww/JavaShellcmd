package com.gqt.GUI;

import javax.swing.*;

public class ShellApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShellExecutor executor = new ShellExecutor();
            ShellView gui = new ShellView(executor);
            gui.setVisible(true);
        });
    }
}
