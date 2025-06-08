package com.mcs.content_storage_service.DTO;

import com.mcs.content_storage_service.Entity.StorageEntity;

import java.time.LocalDateTime;

public class StorageDTO {
    private String bucket;
    private String key;
    private Long size;
    private String contentType;
    private String etag;
    private LocalDateTime lastModified;
    private LocalDateTime createdAt;

    public StorageDTO(StorageEntity storageObject) {
        this.bucket = storageObject.getBucket();
        this.key = storageObject.getObjectKey();
        this.size = storageObject.getSize();
        this.contentType = storageObject.getContentType();
        this.etag = storageObject.getEtag();
        this.lastModified = storageObject.getLastModified();
        this.createdAt = storageObject.getCreatedAt();
    }

    public String getBucket(){
        return bucket;
    }
    public void setBucket(String bucket){
        this.bucket = bucket;
    }

    public String getKey(){
        return key;
    }
    public void setKey(String key){
        this.key = key;
    }

    public Long getSize(){
        return size;
    }
    public void setSize(Long size){
        this.size = size;
    }

    public String getContentType(){
        return contentType;
    }
    public void setContentType(String contentType){
        this.contentType = contentType;
    }

    public String getEtag(){
        return etag;
    }
    public void setEtag(String etag){
        this.etag = etag;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }
    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getObjectKey() {
        return getObjectKey();
    }
}
