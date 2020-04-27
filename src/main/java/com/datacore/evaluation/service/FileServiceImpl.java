package com.datacore.evaluation.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.datacore.evaluation.model.DatacoreFile;
import com.datacore.evaluation.repository.DatacoreFileRepository;

@Service
public class FileServiceImpl implements FileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Value("${files.output.dir}")
	private String outputDirectory;
	
	@Autowired
	private DatacoreFileRepository datacoreFileRepository;
	
	@Override
	public void createFiles() {
		for(int i = 1; i <= 100; i++) {
			DatacoreFile datacoreFile = new DatacoreFile(RandomStringUtils.randomAlphanumeric(8) + ".txt", currentDateTime());
			datacoreFileRepository.save(datacoreFile);
			
			try {
				Files.write(Paths.get(outputDirectory + datacoreFile.getFileName()), datacoreFile.getFileContent().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	@Override
	public void checkEvenIdFiles() {
		List<DatacoreFile> datacoreFiles = new ArrayList<>();
		datacoreFileRepository.findAll().forEach(datacoreFiles::add);
		
		List<DatacoreFile> evenIdFiles = datacoreFiles.stream()
                .filter(file -> file.getId() % 2 == 0)
                .limit(100)
                .collect(Collectors.toList());
		
		
		for(DatacoreFile datacoreFile : evenIdFiles) {
			LOGGER.info("Id: {} --- File Name: {} --- File Content: {}", datacoreFile.getId(), datacoreFile.getFileName(), datacoreFile.getFileContent());
		}
		
	}
	
	private String currentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return now.format(formatter);
	}
	
	
}
