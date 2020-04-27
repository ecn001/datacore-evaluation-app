package com.datacore.evaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.datacore.evaluation.service.FileService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private FileService fileService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileService.createFiles();
		fileService.checkEvenIdFiles();
	}
	
	

}
	