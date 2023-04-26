package com.villamorvinzie.thumbnailgenerator.services.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;

import com.villamorvinzie.thumbnailgenerator.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    private Scalr scalr;

    public ImageServiceImpl(Scalr scalr) {
        this.scalr = scalr;
    }

    @Override
    public void simpleResizeImage(File fileImg, int targetWidth) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(fileImg);
        BufferedImage resizedImg = scalr.resize(bufferedImage, targetWidth);
        File outputfile = new File(String.format("%s.%s", "test", "jpg"));
        ImageIO.write(resizedImg, "jpg", outputfile);
    }

}
