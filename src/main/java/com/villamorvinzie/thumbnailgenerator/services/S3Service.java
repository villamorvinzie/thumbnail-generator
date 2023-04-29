package com.villamorvinzie.thumbnailgenerator.services;

import java.io.File;

public interface S3Service {
    public byte[] getObjectBytes(String key);

    public void putObject(String key, File file);
}
