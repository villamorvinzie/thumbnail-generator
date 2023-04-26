package com.villamorvinzie.thumbnailgenerator;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.villamorvinzie.thumbnailgenerator.services.ImageService;
import com.villamorvinzie.thumbnailgenerator.services.S3Service;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class ThumbnailGeneratorApplication implements ApplicationRunner {

	private ImageService imageService;
	private S3Service s3Service;

	public ThumbnailGeneratorApplication(ImageService imageService, S3Service s3Service) {
		this.imageService = imageService;
		this.s3Service = s3Service;
	}

	public static void main(String[] args) {
		SpringApplication.run(ThumbnailGeneratorApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try (FileOutputStream fos = new FileOutputStream("orig.jpg")) {
			byte[] objBytes = s3Service.getObjectBytes("315412762_9039824512698019_2664958205566996045_n.jpg");
			fos.write(objBytes);
			imageService.simpleResizeImage(new File("orig.jpg"), 320);
		}
	}

}
