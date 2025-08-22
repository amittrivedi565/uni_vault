package com.univault.css.Core;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Manages a queue of files awaiting upload using a bounded thread-safe buffer.
 */
@Component
public class QueueManager {

    private static final int QUEUE_CAPACITY = 100;
    private final BlockingQueue<MultipartFile> fileQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    /**
     * Adds a file to the upload queue.
     *
     * @param file MultipartFile to queue
     * @return true if file was queued, false if queue is full
     */
    public boolean queueFile(MultipartFile file) {
        boolean added = fileQueue.offer(file);
        if (added) {
            System.out.println("File queued: " + file.getOriginalFilename());
        } else {
            System.err.println("Queue is full. File rejected: " + file.getOriginalFilename());
        }
        return added;
    }

    /**
     * Retrieves and removes the next file from the queue, if available.
     *
     * @return Optional containing the file if available, or empty
     */
    public Optional<MultipartFile> pollFile() {
        MultipartFile file = fileQueue.poll();
        if (file != null) {
            System.out.println("File picked: " + file.getOriginalFilename());
        }
        return Optional.ofNullable(file);
    }

    /**
     * Returns the current number of files in the queue.
     */
    public int getQueueSize() {
        return fileQueue.size();
    }
}
