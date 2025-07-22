package com.univault.css.Repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class BucketEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String fileName;
    private String filePath;

    public BucketEntity() {}

    public BucketEntity(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public UUID getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
