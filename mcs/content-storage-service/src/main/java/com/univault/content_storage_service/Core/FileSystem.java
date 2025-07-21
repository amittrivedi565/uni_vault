package com.univault.content_storage_service.Core;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.univault.content_storage_service.Concurrency.ThreadManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSystem {

    private final QueueManager qm;
    private final ThreadManager tm;

    @Autowired
    public FileSystem(QueueManager qm, ThreadManager tm) {
        this.qm = qm;
        this.tm = tm;
    }

    public void writeFileAsync(String dirPath, String fileName, String taskId) {
        Path path = Path.of(dirPath);
        tm.submit_task(() -> {
            try {
                writeFile(path, fileName);
            } catch (Exception e) {
                System.err.println("Upload failed: " + e.getMessage());
            }
        });
    }

    public void writeFile(Path targetDir, String file_name) {
        try {
            MultipartFile file = qm.pickFileForUpload();

            Files.createDirectories(targetDir);

            Path destination = targetDir.resolve(file_name);

            try (InputStream input = file.getInputStream()) {
                Files.copy(input, destination, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File saved to: " + destination);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    public byte[] readFile(String path) {
        try {
            if (path == null || path.isEmpty()) {
                throw new IllegalArgumentException("File path cannot be null or empty");
            }

            Path filePath = Paths.get(path);

            if (!Files.exists(filePath)) {
                throw new RuntimeException("File not found: " + path);
            }

            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to open file for download", e);
        }
    }

}
