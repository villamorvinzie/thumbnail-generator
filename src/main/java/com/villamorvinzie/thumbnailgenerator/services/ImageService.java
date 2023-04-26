package com.villamorvinzie.thumbnailgenerator.services;

import java.io.File;
import java.io.IOException;

public interface ImageService {
    void simpleResizeImage(File fileImg, int targetWidth) throws IOException;
}
