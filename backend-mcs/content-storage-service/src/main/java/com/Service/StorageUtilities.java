package com.Service;

import com.Exceptions.StorageUtilitiesException;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class StorageUtilities {

    public void validateBucket(String bucket) {
        if (bucket == null || bucket.trim().isEmpty()) {
            throw new StorageUtilitiesException("Bucket name cannot be empty");
        }
        if (!bucket.matches("^[a-z0-9][a-z0-9.-]*[a-z0-9]$")) {
            throw new StorageUtilitiesException("Invalid bucket name format");
        }
    }

     public void validateKey(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new StorageUtilitiesException("Object key cannot be empty");
        }
        if (key.length() > 1024) {
            throw new StorageUtilitiesException("Object key too long");
        }
    }

    public String generateFilePath(String key, String bucket){

        // get file name and create a hash.
        String hash = Integer.toHexString(key.hashCode());

        // after hash generated, directory or file path is generated.
        // this is the path where files will be stored.
        // the file path will be /bucket-name/A9/G4/key.
        // example hash is A9G4DCV…, we will 0,2 & 2,4 indexes.
        return String.format("%s/%s/%s/%s",bucket,hash.substring(0,2),hash.substring(2,4),key);
    }

    public String calculateMD5Hash(InputStream inputStream, int chunkSize) throws Exception {

            // creating a message digest object, specifying the algorithm being used.
            MessageDigest md = MessageDigest.getInstance("MD5");

            // array of type bytes, that will store the incoming bytes from the input stream.
            // buffer can only store up to 8192 bytes ~ 8 kb of data at a time.
            byte[] buffer = new byte[chunkSize];

            // status check for how many bytes has been read till now.
            int byteRead;

            // looping through the incoming input stream and storing them onto buffer.
            // after being stored, check if we have reached the end of the bye length.
            // read data in the chunk format; every time a new chunk arrives, hash is updated.
            while((byteRead = inputStream.read(buffer)) != -1){
                md.update(buffer,0,byteRead);
            }

            // computed hash stored buffer digest
            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();

            // iterate over bytes stored in the digest and format them.
            // format them human-readable hexadecimal string.
            for(byte b : digest){
                sb.append(String.format("02x",b));
            }
            return  sb.toString();
    }

    public void deletePhysicalFile(String filePath){
        Path path =  Paths.get(filePath);
        try{
            Files.deleteIfExists(path);
            System.out.println("Files deleted successfully: "+filePath);
        }catch (Exception e){
            throw new StorageUtilitiesException(e.getMessage());
        }
    }
}
