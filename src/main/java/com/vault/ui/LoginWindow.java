package com.vault.ui;


import com.vault.dao.UserDao;
import com.vault.model.User;
import com.vault.util.PasswordUtil;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginWindow() {
        setTitle("Vault Login");
        setSize(400, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(45, 52, 54));

        JLabel titleLabel = new JLabel("Welcome to Virtual Vault");
        titleLabel.setBounds(60, 20, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 80, 100, 25);
        userLabel.setForeground(Color.WHITE);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(140, 80, 200, 25);
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 120, 100, 25);
        passLabel.setForeground(Color.WHITE);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 120, 200, 25);
        panel.add(passwordField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(140, 170, 90, 30);
        loginBtn.setBackground(new Color(9, 132, 227));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        panel.add(loginBtn);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(250, 170, 90, 30);
        registerBtn.setBackground(new Color(0, 184, 148));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);
        panel.add(registerBtn);

        add(panel);

        loginBtn.addActionListener(e -> login());
        registerBtn.addActionListener(e -> register());
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            showError("Username and Password cannot be empty.");
            return;
        }

        UserDao userDao = new UserDao();
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            showError("User does not exist.");
            return;
        }

        if (PasswordUtil.verifyPassword(password, user.getPassword())) {
            SwingUtilities.invokeLater(() -> {
                new VaultDashboard(user).setVisible(true);
                this.dispose();
            });
        } else {
            showError("Incorrect password.");
        }
    }

    private void register() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            showError("Username and Password cannot be empty.");
            return;
        }

        UserDao userDao = new UserDao();
        if (userDao.getUserByUsername(username) != null) {
            showError("Username already exists.");
            return;
        }

        String hashedPass = PasswordUtil.hashPassword(password);
        User newUser = new User(username, hashedPass);

        if (userDao.addUser(newUser)) {
            JOptionPane.showMessageDialog(this, "Registration successful! You can login now.");
        } else {
            showError("Failed to register user.");
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
