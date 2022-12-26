package com.example.upload.uploaddemo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import springfox.documentation.annotations.ApiIgnore;

@RestController
public class DemoController {

	@Autowired
	private WebClient.Builder webClientBuilder;

	@PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> upload(@RequestPart("file") MultipartFile file) throws IOException {
		
		MultipartBodyBuilder builder = new MultipartBodyBuilder();
		builder.part("file", file.getResource());
		
		WebClient webClient = webClientBuilder.build();
        String response = webClient.post()
                .uri("http://localhost:8080/process")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(String.class)   
                .block();
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@ApiIgnore
	@PostMapping(value = "/process", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> process(@RequestPart("file") MultipartFile file) throws IOException {
		String response = new StringBuilder("File Name: ")
				.append(file.getOriginalFilename())
				.append(", File size: ")
				.append(file.getSize())
				.toString();
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
