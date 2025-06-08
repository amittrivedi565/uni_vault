package com.mcs.content_storage_service.Service;
import com.mcs.content_storage_service.DTO.StorageDTO;
import com.mcs.content_storage_service.Entity.StorageEntity;
import com.mcs.content_storage_service.Exceptions.StorageServiceException;
import com.mcs.content_storage_service.Repository.StorageRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
public class StorageService {
    private final StorageRepository repository;
    private final String basePath;
    private final int chunkSize;
    private final StorageUtilities storageUtilities = new StorageUtilities();

    public StorageService(StorageRepository repository,
                          @Value("${storage.base-path}") String basePath,
                          @Value("${storage.chunk-size}") int chunkSize) {
        this.repository = repository;
        this.basePath = basePath;
        this.chunkSize = chunkSize;
        initializeStorage();
    }

    private void initializeStorage() {
        try {
            Path path = Paths.get(basePath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (Exception e) {
            throw new StorageServiceException("Failed to initialize storage", e);
        }
    }

    public StorageDTO uploadFile(String bucket, String key, MultipartFile file) {
        try {
            storageUtilities.validateBucket(bucket);
            storageUtilities.validateKey(key);

            // Generate file-path based on given parameters.
            String filePath = storageUtilities.generateFilePath(key, bucket);

            // Create given path with base directory.
            Path targetPath = Paths.get(basePath, filePath);

            // Ensure directory exists.
            Files.createDirectories(targetPath.getParent());

            // Generate hash for file verification & tempering protection
            String etag = storageUtilities.calculateMD5Hash(file.getInputStream(), chunkSize);

            // Get the data from the input stream, write the data on our system on targeted-path.
            // IOUtils is used to efficiently handle transfer b/w io stream and op stream
            try (InputStream inputStream = file.getInputStream();
                 OutputStream outputStream = Files.newOutputStream(targetPath)) {
                IOUtils.copyLarge(inputStream, outputStream, new byte[chunkSize]);
            }

            StorageEntity storageObject = new StorageEntity(
                    bucket, key, file.getSize(), file.getContentType(), etag, filePath);

            StorageEntity save = repository.save(storageObject);
            return new StorageDTO(save);

        } catch (Exception e) {
            throw new StorageServiceException("Upload File Service Exception", e);
        }
    }
}
