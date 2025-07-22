package com.univault.css.Core;

import com.univault.css.Concurrency.ThreadManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Component
public class FileSystem {

    private final QueueManager queueManager;
    private final ThreadManager threadManager;

    @Autowired
    public FileSystem(QueueManager queueManager, ThreadManager threadManager) {
        this.queueManager = queueManager;
        this.threadManager = threadManager;
    }

    /**
     * Asynchronously writes a file to the specified directory.
     *
     * @param dirPath  target directory path as string
     * @param fileName desired file name
     * @return CompletableFuture with saved Path
     */
    public CompletableFuture<Path> writeFileAsync(String dirPath, String fileName, MultipartFile file) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Ensure target directory exists
                Path targetDir = Paths.get(dirPath);
                Files.createDirectories(targetDir);

                // Resolve the full file path
                Path filePath = targetDir.resolve(fileName);

                // Use input stream to safely transfer content
                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }

                return filePath;
            } catch (Exception e) {
                System.err.println("❌ Async upload failed: " + e.getMessage());
                throw new CompletionException("Async file write failed", e);
            }
        }, threadManager.getExecutor());
    }


    /**
     * Synchronously writes a picked file to the target directory.
     *
     * @param targetDir the destination directory path
     * @param fileName  the desired file name
     * @return Path to the saved file
     */
    public Path writeFile(Path targetDir, String fileName) {
        MultipartFile file = queueManager.pollFile()
                .orElseThrow(() -> new IllegalArgumentException("No file selected or file is empty"));

        try {
            Files.createDirectories(targetDir);
            Path destination = targetDir.resolve(fileName);

            try (InputStream input = file.getInputStream()) {
                Files.copy(input, destination, StandardCopyOption.REPLACE_EXISTING);
            }

            System.out.println("✅ File saved at: " + destination);
            return destination;

        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to write file due to I/O error", e);
        }
    }


    /**
     * Reads a file from the given path and returns its byte content.
     *
     * @param path file path as string
     * @return byte array of the file content
     */
    public byte[] readFile(String path) {
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }

        Path filePath = Paths.get(path);
        if (!Files.exists(filePath)) {
            throw new RuntimeException("File not found: " + path);
        }

        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + path, e);
        }
    }
}
