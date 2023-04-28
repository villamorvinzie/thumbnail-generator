package com.villamorvinzie.thumbnailgenerator.functions;

import java.util.Map;
import java.util.function.Consumer;

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
    public Consumer<Map<String, String>> handles() {
        return (in) -> {
            log.info(in);
        };
    }
}
