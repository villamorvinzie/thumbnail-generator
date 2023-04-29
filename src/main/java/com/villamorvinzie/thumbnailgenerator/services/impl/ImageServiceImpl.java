package com.villamorvinzie.thumbnailgenerator.services.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;

import com.villamorvinzie.thumbnailgenerator.services.ImageService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ImageServiceImpl implements ImageService {

    private Scalr scalr;

    public ImageServiceImpl(Scalr scalr) {
        this.scalr = scalr;
    }

    @Override
    public File simpleResizeImage(File fileImg, int targetWidth, String targetPath) throws IOException {
        String defaultExt = "jpg";
        String defaultFilename = "test";
        String fullFilename = fileImg.getName();
        BufferedImage bufferedImage = ImageIO.read(fileImg);

        log.info("Generating thumbnail for image: {}", fullFilename);
        BufferedImage resizedImg = scalr.resize(bufferedImage, targetWidth);
        String filename = getFilename(fullFilename).orElse(defaultFilename);
        String ext = getFileExtension(fullFilename).orElse(defaultExt);
        File outputfile = new File(String.format("%s/%s.%s", targetPath, filename, ext));

        log.info("Writing image into disk: {}", outputfile.getAbsolutePath());
        ImageIO.write(resizedImg, ext, outputfile);
        return outputfile;
    }

    private Optional<String> getFileExtension(String fullFilename) {
        return Optional.ofNullable(fullFilename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(f.lastIndexOf(".") + 1));
    }

    private Optional<String> getFilename(String fullFilename) {
        return Optional.ofNullable(fullFilename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(0, f.lastIndexOf(".")));
    }
}
