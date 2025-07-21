package com.univault.content_storage_service.Core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class QueueManager {
    private final BlockingQueue<MultipartFile> queue = new ArrayBlockingQueue<>(100);

    public void addFileToQueue(MultipartFile file) {
        System.out.println("File queued: " + file.getOriginalFilename());
        queue.offer(file);
    }

    public MultipartFile pickFileForUpload() {
        MultipartFile file = queue.poll();
        if (file != null) {
            System.out.println("Picked file: " + file.getOriginalFilename());
        }
        return file;
    }
}