package com.datacore.evaluation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DATACORE_FILE")
public class DatacoreFile implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7011140608775812707L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String fileName;
	
	private String fileContent;
	
	public DatacoreFile() {
		
	}
	
	public DatacoreFile(String fileName, String fileContent) {
		this.fileName = fileName;
		this.fileContent = fileContent;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

}
