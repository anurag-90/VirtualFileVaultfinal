package com.vault.ui;


import com.vault.model.User;
import com.vault.util.CryptoUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VaultDashboard extends JFrame {
    private final User loggedInUser;
    private final String vaultFolderPath = "vault/";

    public VaultDashboard(User user) {
        this.loggedInUser = user;

        setTitle("Vault Dashboard - Welcome " + user.getUsername());
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 39, 46));

        JLabel titleLabel = new JLabel("Secure Vault Dashboard");
        titleLabel.setBounds(130, 20, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel);

        JButton uploadBtn = new JButton("Encrypt & Upload File");
        uploadBtn.setBounds(150, 80, 200, 30);
        uploadBtn.setBackground(new Color(9, 132, 227));
        uploadBtn.setForeground(Color.WHITE);
        uploadBtn.setFocusPainted(false);
        panel.add(uploadBtn);

        JButton downloadBtn = new JButton("Decrypt & Download File");
        downloadBtn.setBounds(150, 130, 200, 30);
        downloadBtn.setBackground(new Color(0, 184, 148));
        downloadBtn.setForeground(Color.WHITE);
        downloadBtn.setFocusPainted(false);
        panel.add(downloadBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(200, 190, 100, 30);
        logoutBtn.setBackground(Color.RED);
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        panel.add(logoutBtn);

        uploadBtn.addActionListener(this::handleUpload);
        downloadBtn.addActionListener(this::handleDownload);
        logoutBtn.addActionListener(e -> {
            this.dispose();
            new com.vault.ui.LoginWindow().setVisible(true);
        });

        add(panel);
    }

    private void handleUpload(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select file to encrypt and store");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            Path sourcePath = selectedFile.toPath();
            Path targetPath = Paths.get(vaultFolderPath + selectedFile.getName() + ".enc");

            try {
                Files.createDirectories(Paths.get(vaultFolderPath));
                CryptoUtil.encryptFile(sourcePath, targetPath);
                JOptionPane.showMessageDialog(this, "File encrypted and saved successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Encryption Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleDownload(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(vaultFolderPath);
        fileChooser.setDialogTitle("Select encrypted file to decrypt");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File encryptedFile = fileChooser.getSelectedFile();
            String decryptedName = encryptedFile.getName().replace(".enc", "_decrypted");

            Path sourcePath = encryptedFile.toPath();
            Path targetPath = Paths.get("downloads/" + decryptedName);

            try {
                Files.createDirectories(Paths.get("downloads/"));
                CryptoUtil.decryptFile(sourcePath, targetPath);
                JOptionPane.showMessageDialog(this, "File decrypted and saved to downloads folder.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Decryption Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
