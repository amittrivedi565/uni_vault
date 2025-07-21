package com.univault.content_storage_service.Bucket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/css")
public class BucketController {
    private final BucketService bucketService;
    private final FileValidator fileValid;

    @Autowired
    public BucketController(BucketService bucketService, FileValidator fileValid) {
        this.bucketService = bucketService;
        this.fileValid = fileValid;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) {
        try {
            FileValidator.ValidationResult result = fileValid.isValidPdf(file);

            if (!result.valid()) {
                System.err.println("Rejected file: " + result.message());
                return ResponseEntity.badRequest().body(result.message());
            }

            UUID fileId = bucketService.uploadFileService(file);
            return ResponseEntity.ok("File queued and upload started," + " Task ID: " + fileId);
        } catch (Exception e) {
            System.err.println("Upload failed: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable UUID id) {
        byte[] data = bucketService.downloadFileService(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "file.pdf");
        headers.setContentLength(data.length);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

}