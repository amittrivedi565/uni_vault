package com.univault.css.Bucket;

import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class PathGenerator {

    private static final String BUCKET_ROOT = "uploads";
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    /**
     * Generates a hashed directory path under the uploads root.
     */
    public String generatePath() {
        String hash = sha256Hex(UUID.randomUUID().toString());

        Path path = Path.of(
                BUCKET_ROOT,
                hash.substring(0, 2),
                hash.substring(2, 4),
                hash.substring(4, 6)
        );

        try {
            Files.createDirectories(path);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create directory: " + path.toAbsolutePath(), e);
        }

        return path.toString();
    }

    /**
     * Generates a timestamped filename with default fallback values.
     */
    public String generateFileName(String baseName, String extension) {
        String safeBase = (baseName == null || baseName.isBlank()) ? "uni_vault" : baseName;
        String safeExt = (extension == null || extension.isBlank()) ? "pdf" : extension;

        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String uuidPart = UUID.randomUUID().toString().substring(0, 6);

        return safeBase + "_" + timestamp + "_" + uuidPart + "." + safeExt;
    }

    /**
     * Generates SHA-256 hash in hex format.
     */
    private String sha256Hex(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hashBytes) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generating SHA-256 hash", e);
        }
    }
}
