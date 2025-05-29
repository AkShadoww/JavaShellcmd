package com.gqt.GUI;

import javax.swing.SwingUtilities;

public class MainAppGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
