package com.Service;

import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.Queue;

public class FileQueueService {
    private final Queue<MultipartFile> fileQueue = new LinkedList<>();

    public synchronized void enqueue(MultipartFile file){
        fileQueue.add(file);
    }

    public synchronized MultipartFile dequeue() {
        return fileQueue.poll();
    }

    public synchronized int getQueueSize() {
        return fileQueue.size();
    }
}