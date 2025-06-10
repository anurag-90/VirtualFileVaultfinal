package com.vault.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Base64;

public class CryptoUtil {
    private static final String ALGORITHM = "AES";
    private static final byte[] keyBytes = new byte[16]; // 128-bit key

    static {
        SecureRandom random = new SecureRandom();
        random.nextBytes(keyBytes); // Generates a random key when the app runs
    }

    private static SecretKey getKey() {
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    public static byte[] encrypt(byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, getKey());
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getKey());
        return cipher.doFinal(data);
    }

    public static void encryptFile(Path inputFile, Path outputFile) throws Exception {
        byte[] fileBytes = Files.readAllBytes(inputFile);
        byte[] encryptedBytes = encrypt(fileBytes);
        Files.write(outputFile, encryptedBytes);
    }

    public static void decryptFile(Path inputFile, Path outputFile) throws Exception {
        byte[] encryptedBytes = Files.readAllBytes(inputFile);
        byte[] decryptedBytes = decrypt(encryptedBytes);
        Files.write(outputFile, decryptedBytes);
    }

    public static String encryptString(String plainText) throws Exception {
        byte[] encryptedBytes = encrypt(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decryptString(String encryptedText) throws Exception {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = decrypt(decodedBytes);
        return new String(decryptedBytes);
    }
}
