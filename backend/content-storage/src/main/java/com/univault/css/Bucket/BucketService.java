package com.univault.css.Bucket;

import com.univault.css.Core.FileSystem;
import com.univault.css.Core.QueueManager;
import com.univault.css.Repository.BucketEntity;
import com.univault.css.Repository.BucketRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Service layer for handling file uploads and downloads.
 */
@Service
public class BucketService {

    private final BucketRepo bucketRepo;
    private final QueueManager queueManager;
    private final FileSystem fileSystem;
    private final PathGenerator pathGenerator;

    @Autowired
    public BucketService(
            BucketRepo bucketRepo,
            QueueManager queueManager,
            FileSystem fileSystem,
            PathGenerator pathGenerator
    ) {
        this.bucketRepo = bucketRepo;
        this.queueManager = queueManager;
        this.fileSystem = fileSystem;
        this.pathGenerator = pathGenerator;
    }

    /**
     * Handles file upload: validates, generates path, stores metadata, and triggers async file write.
     *
     * @param file Multipart file to upload
     * @return UUID of the uploaded file (task ID)
     */
    public CompletableFuture<UUID> uploadFileService(MultipartFile file) {
        try {
            queueManager.queueFile(file);
            String fullPath = pathGenerator.generatePath();
            String fileName = pathGenerator.generateFileName("uni_vault", "pdf");

            return fileSystem.writeFileAsync(fullPath, fileName, file)
                    .thenApply(path -> {
                        BucketEntity meta = new BucketEntity();
                        meta.setFileName(fileName);
                        meta.setFilePath(fullPath);
                        BucketEntity saved = bucketRepo.save(meta);
                        System.out.println("✅ Metadata saved for: " + fileName);
                        System.out.println(saved.getId());
                        return saved.getId();
                    });

        } catch (Exception e) {
            CompletableFuture<UUID> failedFuture = new CompletableFuture<>();
            failedFuture.completeExceptionally(
                    new RuntimeException("Failed to upload file: " + e.getMessage(), e)
            );
            return failedFuture;
        }
    }


    /**
     * Handles file download based on its UUID.
     *
     * @param id UUID of the file
     * @return byte[] containing file contents
     */
    public byte[] downloadFileService(UUID id) {
        try {
            BucketEntity meta = bucketRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("File metadata not found for ID: " + id));

            String fullFilePath = meta.getFilePath() + File.separator + meta.getFileName();
            return fileSystem.readFile(fullFilePath);

        } catch (Exception e) {
            throw new RuntimeException("Download failed: " + e.getMessage(), e);
        }
    }
}
