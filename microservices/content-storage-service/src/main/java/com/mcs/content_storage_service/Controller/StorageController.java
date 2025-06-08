package com.mcs.content_storage_service.Controller;

import com.mcs.content_storage_service.DTO.StorageDTO;
import com.mcs.content_storage_service.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class StorageController {
    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService){
        this.storageService = storageService;
    }

    @PostMapping("/upload/{bucket}/{key:.+}")
    public ResponseEntity<StorageDTO> uploadFile(
            @PathVariable String bucket,
            @PathVariable String key,
            @RequestParam("file")MultipartFile file
            ){
        StorageDTO response = storageService.uploadFile(bucket,key,file);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/download/{bucket}/{key:.+}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String bucket,
            @PathVariable String key) {
        try {
            Resource resource = storageService.downloadFile(bucket, key);

            // Set the headers for the response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

