package com.example.upload.uploaddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableWebMvc
@EnableSwagger2 	
public class UploadDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadDemoApplication.class, args);
	}

}
