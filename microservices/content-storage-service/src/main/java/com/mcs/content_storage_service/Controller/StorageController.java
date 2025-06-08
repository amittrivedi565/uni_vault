package com.mcs.content_storage_service.Controller;

import com.mcs.content_storage_service.DTO.StorageDTO;
import com.mcs.content_storage_service.Service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/storage")
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService){
        this.storageService = storageService;
    }

    @PostMapping("/{bucket}/{key:.+}")
    public ResponseEntity<StorageDTO> uploadFile(
            @PathVariable String bucket,
            @PathVariable String key,
            @RequestParam("file")MultipartFile file
            ){
        StorageDTO response = storageService.uploadFile(bucket,key,file);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
