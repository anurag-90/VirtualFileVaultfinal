package com.vault.model;

import java.time.LocalDateTime;

public class VaultFile {
    private int id;
    private String ownerUsername;
    private String originalFilename;
    private String encryptedFilename;
    private LocalDateTime uploadTime;

    public VaultFile() {}

    public VaultFile(String ownerUsername, String originalFilename, String encryptedFilename, LocalDateTime uploadTime) {
        this.ownerUsername = ownerUsername;
        this.originalFilename = originalFilename;
        this.encryptedFilename = encryptedFilename;
        this.uploadTime = uploadTime;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getEncryptedFilename() {
        return encryptedFilename;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public void setEncryptedFilename(String encryptedFilename) {
        this.encryptedFilename = encryptedFilename;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }
}
