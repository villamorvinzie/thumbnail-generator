package com.villamorvinzie.thumbnailgenerator.services.impl;

import java.io.File;

import org.springframework.stereotype.Service;

import com.villamorvinzie.thumbnailgenerator.config.S3Config;
import com.villamorvinzie.thumbnailgenerator.services.S3Service;

import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;

@Service
@Log4j2
public class S3ServiceImpl implements S3Service {

    private S3Config s3Config;
    private S3Client s3Client;

    public S3ServiceImpl(S3Config s3Config, S3Client s3Client) {
        this.s3Config = s3Config;
        this.s3Client = s3Client;
    }

    @Override
    public byte[] getObjectBytes(String key) {
        log.info("Downloading object with key: {}, bucket: {}", key, s3Config.getBucketName());
        return s3Client.getObjectAsBytes(builder -> {
            builder.bucket(s3Config.getBucketName());
            builder.key(key);
        }).asByteArray();
    }

    @Override
    public void putObject(String key, File file) {
        log.info("Uploading object with key: {}, file: {}", key, file.getAbsolutePath());
        s3Client.putObject(putObjReq -> {
            putObjReq.bucket(s3Config.getBucketName());
            putObjReq.key(key);
            putObjReq.build();
        }, RequestBody.fromFile(file));
    }

}
