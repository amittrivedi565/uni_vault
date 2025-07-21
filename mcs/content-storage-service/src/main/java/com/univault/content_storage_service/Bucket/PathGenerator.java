package com.univault.content_storage_service.Bucket;

import org.springframework.stereotype.Component;

import java.io.File;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class PathGenerator {
    private static final String BUCKET_NAME = "uploads";

    public String pg() {
        // Generate a hash from UUID
        String hash = hash(UUID.randomUUID().toString());

        // Use first 6 chars for directory nesting
        String path = BUCKET_NAME + File.separator +
                hash.substring(0, 2) + File.separator +
                hash.substring(2, 4) + File.separator +
                hash.substring(4, 6);

        File dir = new File(path);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new RuntimeException("Failed to create directory: " + dir.getAbsolutePath());
        }

        return path;
    }

    private String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Hash error", e);
        }
    }

    public String generate_file_name(String baseName, String extension) {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String uuidPart = UUID.randomUUID().toString().substring(0, 6);

        return baseName + "_" + timestamp + "_" + uuidPart + "." + extension;
    }
}
