package com.mcs.content_storage_service.Exceptions;

public class StorageServiceException extends RuntimeException {
    public StorageServiceException(String message) {
        super(message);
    }

    public StorageServiceException(String message, Exception ex) {
        super(message, ex);
    }
}



