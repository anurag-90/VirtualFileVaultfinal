package com.vault.MainApp.java;


import com.formdev.flatlaf.FlatLightLaf;
import com.vault.ui.LoginWindow;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        // Set FlatLaf Light look and feel for modern UI
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize Look and Feel: " + ex.getMessage());
        }

        // Launch the LoginWindow on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);
        });
    }
}
