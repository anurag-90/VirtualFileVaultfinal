# 🔐 Virtual File Vault

A secure Java-based desktop application for storing, encrypting, and managing personal files with user authentication and password hashing.

## 🧰 Tech Stack

- **Language:** Java (JDK 17+)
- **GUI:** Swing
- **Database:** MySQL
- **Encryption:** AES-based custom crypto utility
- **Password Hashing:** SHA-256 (PasswordUtil)
- **Build Tool:** IntelliJ IDEA
- **Version Control:** Git & GitHub

## ✨ Features

- 🔑 Secure login and registration with password hashing
- 🔒 AES-based file encryption & decryption
- 🗂️ File storage linked to individual user accounts
- 🖼️ Modern, clean UI using FlatLaf
- 📁 Add, view, delete, and decrypt files easily
- 💾 Persistent database using MySQL

## 📸 Screenshots

> *(Add screenshots here later using Markdown image syntax)*




## 🚀 How to Run

1. **Clone the Repo:**
   ```bash
   git clone https://github.com/anurag-90/VirtualFileVaultfinal.git


2 : Set Up the Database:

CREATE DATABASE vault;
USE vault;

CREATE TABLE users (
username VARCHAR(255) PRIMARY KEY,
password_hash VARCHAR(64)
);

CREATE TABLE files (
id INT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(255),
filename VARCHAR(255),
encrypted_data LONGBLOB,
FOREIGN KEY (username) REFERENCES users(username)
);

3 : Configure DB Credentials in DBUtil.java:

private static final String URL = "jdbc:mysql://localhost:3306/vault";
private static final String USER = "root";
private static final String PASSWORD = "your_password";


4 : Build and Run using IntelliJ:

Mark src as source root.

Run MainApp.java.


📂 Project Structure

com.vault
├── dao
│   ├── FileDao.java
│   └── UserDao.java
├── model
│   ├── User.java
│   └── VaultFile.java
├── ui
│   ├── LoginWindow.java
│   └── VaultDashboard.java
├── util
│   ├── DBUtil.java
│   ├── CryptoUtil.java
│   └── PasswordUtil.java
└── MainApp.java


🧪 Testing
✅ Registered new users
✅ Logged in with correct/incorrect credentials
✅ Uploaded and viewed encrypted files
✅ Verified decryption works correctly
✅ Tested database consistency





