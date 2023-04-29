package com.villamorvinzie.thumbnailgenerator.functions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.villamorvinzie.thumbnailgenerator.services.ImageService;
import com.villamorvinzie.thumbnailgenerator.services.S3Service;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ThumbnailGeneratorHandler {

    private ImageService imageService;
    private S3Service s3Service;

    public ThumbnailGeneratorHandler(ImageService imageService, S3Service s3Service) {
        this.imageService = imageService;
        this.s3Service = s3Service;
    }

    @Bean
    public Consumer<String> handle() {

        return (in) -> {
            final String tmp = "/tmp/";
            final String thumbnails = "thumbnails";
            final String uploadsPath = tmp + "uploads";
            final String modifiedTargetPath = tmp + thumbnails;

            // Create upload and modified directory if it does not exist.
            try {
                Files.createDirectories(Path.of(uploadsPath));
                Files.createDirectories(Path.of(modifiedTargetPath));
            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONObject root = new JSONObject(in);
            JSONArray records = root.getJSONArray("Records");

            records.forEach(record -> {

                try {

                    JSONObject rec = (JSONObject) record;
                    JSONObject s3 = rec.getJSONObject("s3");
                    JSONObject object = s3.getJSONObject("object");
                    String key = URLDecoder.decode(object.getString("key"), "UTF-8");
                    String tmpKey = tmp.concat(key);

                    log.info("Creating new file: {}", tmpKey);
                    File file = new File(tmpKey);
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    byte[] objBytes = s3Service.getObjectBytes(key);
                    fos.write(objBytes);
                    File outputFile = imageService.simpleResizeImage(file, 320, modifiedTargetPath);

                    String newObjKey = String.format("%s/%s", thumbnails, outputFile.getName());
                    s3Service.putObject(newObjKey, outputFile);

                    fos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

        };
    }
}
