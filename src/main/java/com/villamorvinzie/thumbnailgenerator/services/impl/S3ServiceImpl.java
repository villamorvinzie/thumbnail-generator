package com.villamorvinzie.thumbnailgenerator.services.impl;

import org.springframework.stereotype.Service;

import com.villamorvinzie.thumbnailgenerator.config.S3Config;
import com.villamorvinzie.thumbnailgenerator.services.S3Service;

import software.amazon.awssdk.services.s3.S3Client;

@Service
public class S3ServiceImpl implements S3Service {

    private S3Config s3Config;
    private S3Client s3Client;

    public S3ServiceImpl(S3Config s3Config, S3Client s3Client) {
        this.s3Config = s3Config;
        this.s3Client = s3Client;
    }

    @Override
    public byte[] getObjectBytes(String key) {
        return s3Client.getObjectAsBytes(builder -> {
            builder.bucket(s3Config.getBucketName());
            builder.key(key);
        }).asByteArray();
    }

}
