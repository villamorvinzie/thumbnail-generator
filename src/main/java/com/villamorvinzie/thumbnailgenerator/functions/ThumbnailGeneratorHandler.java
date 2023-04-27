package com.villamorvinzie.thumbnailgenerator.functions;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.villamorvinzie.thumbnailgenerator.services.ImageService;
import com.villamorvinzie.thumbnailgenerator.services.S3Service;

@Component
public class ThumbnailGeneratorHandler {

    private ImageService imageService;
    private S3Service s3Service;

    public ThumbnailGeneratorHandler(ImageService imageService, S3Service s3Service) {
        this.imageService = imageService;
        this.s3Service = s3Service;
    }

    @Bean
    public Supplier<String> asd(){
        return ()->"ASDASD";
    }

    @Bean
    public Function<String, String> handle() {
        return (in) -> "out";
    }

    @Bean
    public Consumer<String> handles() {
        return (in) -> {
        };
    }
}
