package com.villamorvinzie.thumbnailgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThumbnailGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThumbnailGeneratorApplication.class, args);
	}

	// @Override
	// public void run(ApplicationArguments args) throws Exception {
	// try (FileOutputStream fos = new FileOutputStream("orig.jpg")) {
	// byte[] objBytes =
	// s3Service.getObjectBytes("315412762_9039824512698019_2664958205566996045_n.jpg");
	// fos.write(objBytes);
	// imageService.simpleResizeImage(new File("orig.jpg"), 320);
	// }
	// }

}
