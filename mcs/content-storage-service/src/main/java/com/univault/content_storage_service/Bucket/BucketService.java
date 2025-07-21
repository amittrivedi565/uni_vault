package com.univault.content_storage_service.Bucket;

import com.univault.content_storage_service.Core.FileSystem;
import com.univault.content_storage_service.Core.QueueManager;
import com.univault.content_storage_service.Repository.BucketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.univault.content_storage_service.Repository.BucketRepo;

import java.io.File;
import java.util.UUID;

@Service
public class BucketService {

    private final BucketRepo br;
    private final QueueManager qm;
    private final FileSystem fs;
    private final PathGenerator pg;

    @Autowired
    public BucketService(BucketRepo bucketRepo, QueueManager qm, FileSystem fs, PathGenerator pg) {
        this.br = bucketRepo;
        this.qm = qm;
        this.fs = fs;
        this.pg = pg;
    }

    public UUID uploadFileService(MultipartFile file) {
       try{
           qm.addFileToQueue(file);

           String fullPath = pg.pg();
           String taskId = UUID.randomUUID().toString();
           String fileName = pg.generate_file_name("uni_vault","pdf");

           BucketEntity meta = new BucketEntity();
           meta.setFileName(fileName);
           meta.setFilePath(fullPath);

           br.save(meta);

           fs.writeFileAsync(fullPath,fileName,taskId);
           return meta.getId();

       } catch (RuntimeException e) {
           throw new RuntimeException(e.getMessage());
       }
    }

    public byte[] downloadFileService(UUID id) {
        try {
            BucketEntity meta = br.findById(id)
                    .orElseThrow(() -> new RuntimeException("File metadata not found for id: " + id));

            String fullFilePath = meta.getFilePath() + File.separator + meta.getFileName();

            return fs.readFile(fullFilePath);

        } catch (RuntimeException e) {
            throw new RuntimeException("Download failed: " + e.getMessage(), e);
        }
    }

}
