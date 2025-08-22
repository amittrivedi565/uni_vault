package com.univault.css.Bucket;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Component
public class FileValidator {

    public record ValidationResult(boolean valid, String message) {}

    public ValidationResult isValidPdf(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return new ValidationResult(false, "No file provided.");
        }

        if (!file.getContentType().equals("application/pdf")) {
            return new ValidationResult(false, "Only PDF files are allowed.");
        }

        String filename = file.getOriginalFilename();
        if (filename == null || !filename.toLowerCase().endsWith(".pdf")) {
            return new ValidationResult(false, "Invalid file name or extension.");
        }

        try (InputStream input = file.getInputStream()) {
            byte[] header = input.readNBytes(4);
            if (new String(header).equals("%PDF")) {
                return new ValidationResult(true, "Valid PDF.");
            }
            return new ValidationResult(false, "Invalid PDF file header.");
        } catch (Exception e) {
            return new ValidationResult(false, "Failed to read file.");
        }
    }
}
