package com.univault.css.Bucket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Handles file upload and download endpoints for the content storage system.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BucketController {

    private final BucketService bucketService;
    private final FileValidator fileValidator;

    @Autowired
    public BucketController(BucketService bucketService, FileValidator fileValidator) {
        this.bucketService = bucketService;
        this.fileValidator = fileValidator;
    }

    /**
     * Uploads a PDF file, validates it, and queues it for asynchronous storage.
     *
     * @param file uploaded multipart file
     * @return ResponseEntity with upload result message
     */
    @PostMapping("/uploads")
    public CompletableFuture<ResponseEntity<Map<String, UUID>>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            FileValidator.ValidationResult validation = fileValidator.isValidPdf(file);

            if (!validation.valid()) {
                System.err.println("Rejected file: " + validation.message());
                return CompletableFuture.completedFuture(ResponseEntity
                        .badRequest()
                        .build());
            }

            // Handle the async upload
            return bucketService.uploadFileService(file)
                    .thenApply(uuid -> ResponseEntity.ok(Map.of("id", uuid)))
                    .exceptionally(ex -> {
                        System.err.println("Upload failed: " + ex.getMessage());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(Map.of("error", UUID.fromString("00000000-0000-0000-0000-000000000000")));
                    });
        } catch (Exception e) {
            System.err.println("Upload failed: " + e.getMessage());
            return CompletableFuture.completedFuture(
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
            );
        }
    }
    /**
     * Downloads a previously uploaded file by its UUID.
     *
     * @param id UUID of the file
     * @return ResponseEntity containing the file's byte content and headers
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable UUID id) {
        try {
            byte[] fileData = bucketService.downloadFileService(id);

            if (fileData == null || fileData.length == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition
                    .attachment()
                    .filename("file.pdf")
                    .build());
            headers.setContentLength(fileData.length);

            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);

        } catch (Exception e) {
            System.err.println("Download failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
