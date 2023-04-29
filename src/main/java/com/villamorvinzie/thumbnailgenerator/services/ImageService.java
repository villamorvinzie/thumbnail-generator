package com.villamorvinzie.thumbnailgenerator.services;

import java.io.File;
import java.io.IOException;

public interface ImageService {
    File simpleResizeImage(File fileImg, int targetWidth, String targetPath) throws IOException;
}
