package com.vault.dao;

import com.vault.model.VaultFile;
import com.vault.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileDao {

    public boolean addFile(VaultFile file) {
        String sql = "INSERT INTO files (owner_username, original_filename, encrypted_filename, upload_time) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, file.getOwnerUsername());
            stmt.setString(2, file.getOriginalFilename());
            stmt.setString(3, file.getEncryptedFilename());
            stmt.setTimestamp(4, Timestamp.valueOf(file.getUploadTime()));

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<VaultFile> getFilesByUser(String username) {
        List<VaultFile> fileList = new ArrayList<>();
        String sql = "SELECT * FROM files WHERE owner_username = ? ORDER BY upload_time DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                VaultFile file = new VaultFile();
                file.setId(rs.getInt("id"));
                file.setOwnerUsername(rs.getString("owner_username"));
                file.setOriginalFilename(rs.getString("original_filename"));
                file.setEncryptedFilename(rs.getString("encrypted_filename"));
                file.setUploadTime(rs.getTimestamp("upload_time").toLocalDateTime());

                fileList.add(file);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fileList;
    }

    public boolean deleteFile(int fileId) {
        String sql = "DELETE FROM files WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, fileId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
