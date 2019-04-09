package com.dell.okb.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ReadConfig {
	
	private static final Logger logger = Logger.getLogger(ReadConfig.class);
	
	String result = "";
	InputStream inputStream;
	
	public String getPropValues(String propName) throws IOException {
	try {
		
		Properties prop = new Properties();
		//String propFileName = "SIT1-config.properties";

		inputStream =  new FileInputStream("C:\\OKB_Taxonomy\\jar-config.properties");
		//inputStream =  new FileInputStream("/app/IntegratedSalesCat/request_jar/update/to_run/config.properties");
		//
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file not found ");
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
