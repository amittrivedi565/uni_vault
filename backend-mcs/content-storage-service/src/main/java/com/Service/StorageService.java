package com.Service;
import com.DTO.StorageDTO;
import com.Entity.StorageEntity;
import com.Exceptions.StorageServiceException;
import com.Repository.StorageRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class StorageService {
    private final StorageRepository repository;
    private final String basePath;
    private final int chunkSize;
    private final StorageUtilities storageUtilities = new StorageUtilities();

    @Autowired
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

            // Generate hash for file verification and tempering protection
            String etag = storageUtilities.calculateMD5Hash(file.getInputStream(), chunkSize);

            // Get the data from the input stream, write the data on our system on targeted-path.
            // IOUtils is used to efficiently handle transfer b/w io stream and op stream
            try (InputStream inputStream = file.getInputStream();
                 OutputStream outputStream = Files.newOutputStream(targetPath)) {
                IOUtils.copyLarge(inputStream, outputStream, new byte[chunkSize]);
            }

            StorageEntity storageObject = new StorageEntity(
                    bucket, key, file.getSize(), file.getContentType(), etag, filePath);

            Optional<StorageEntity> existing = repository.findByBucketAndObjectKey(bucket,key);

            if(existing.isPresent()) {
                StorageEntity existingObj = existing.get();
                storageUtilities.deletePhysicalFile(existingObj.getFilePath());
                existingObj.setSize(existingObj.getSize());
                existingObj.setContentType(existingObj.getContentType());
                existingObj.setEtag(etag);
                existingObj.setFilePath(filePath);
                existingObj.setLastModified(LocalDateTime.now());
                storageObject = repository.save(existingObj);
            }else{
               storageObject = repository.save(storageObject);
            }
            return new StorageDTO(storageObject);

        } catch (Exception e) {
            throw new StorageServiceException("Upload File Service Exception", e);
        }
    }

    public Resource downloadFile(String bucket, String key) {
        try {
            // Get a file path for the required file/resource from the database
            Optional<StorageEntity> storageObject = repository.findByBucketAndObjectKey(bucket, key);

            if (storageObject.isEmpty()) {
                throw new StorageServiceException("No storage object found for key: " + key);
            }

            // Get the full directory
            Path filePath = Paths.get(basePath, storageObject.get().getFilePath());
            System.out.println("Attempting to download file from path: " + filePath);

            if (!Files.exists(filePath)) {
                throw new StorageServiceException("Physical file not found with corresponding details");
            }

            // Stream the data
            return new InputStreamResource(Files.newInputStream(filePath));

        } catch (Exception e) {
            throw new StorageServiceException("downloadFile service error: ", e);
        }
    }

}
