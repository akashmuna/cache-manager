package com.Dell.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {
	String result = "";
	InputStream inputStream;
	
	public String getPropValues(String propName) throws IOException {
	try {
		Properties prop = new Properties();
		String propFileName = "config.properties";

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}

		result = prop.getProperty(propName);
		
	} catch (Exception e) {
		System.out.println("Exception: " + e);
	} finally {
		inputStream.close();
	}
	return result;
}

}
