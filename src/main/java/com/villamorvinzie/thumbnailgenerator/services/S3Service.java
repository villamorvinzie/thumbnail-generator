package com.villamorvinzie.thumbnailgenerator.services;

public interface S3Service {
    public byte[] getObjectBytes(String key);
}
