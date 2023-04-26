package com.villamorvinzie.thumbnailgenerator.config;

import org.imgscalr.Scalr;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageConfig {

    @Bean
    public Scalr getScalr() {
        return new Scalr();
    }
}
