package com.datacore.evaluation.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.datacore.evaluation.model.DatacoreFile;

public interface FileService {
	
	CompletableFuture<List<DatacoreFile>> createFiles(int begin, int end);
	
	void checkEvenIdFiles();

}
