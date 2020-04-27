package com.datacore.evaluation;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.datacore.evaluation.model.DatacoreFile;
import com.datacore.evaluation.service.FileService;

@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {
	
	@Autowired
	private FileService fileService;
	
    @Bean("threadPoolTaskExecutor")
    public TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(25);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("AsyncThread-");
        return executor;
    }
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CompletableFuture<List<DatacoreFile>> firstHalf = fileService.createFiles(1,50);
		CompletableFuture<List<DatacoreFile>> secondHalf = fileService.createFiles(51,100);
		 
		CompletableFuture.allOf(firstHalf, secondHalf).join();
		
		fileService.checkEvenIdFiles();
	}
	

}
	