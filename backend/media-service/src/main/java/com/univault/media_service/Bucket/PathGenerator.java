package com.univault.media_service.Bucket;

import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class PathGenerator {
    private static final String BUCKET_ROOT = "uploads";

    public String generatePath() {
        // Generate a random UUID
        String uuid = UUID.randomUUID().toString().replace("-", "");

        // Split into nested folders
        return BUCKET_ROOT + "/"
                + uuid.substring(0, 2) + "/"
                + uuid.substring(2, 4) + "/"
                + uuid;
    }
}