package com.antazri.main.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Loader {

	private String fileName;
	private Properties prop = new Properties();
	private FileInputStream fis;
	

	public Loader(String fileName) {
		this.fileName = "ressources.config.properties";
		try {
			fis = new FileInputStream(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFileName() {
		return this.fileName;
	}

	public void closeStream() throws Exception {
		this.fis.close();
	}

}
