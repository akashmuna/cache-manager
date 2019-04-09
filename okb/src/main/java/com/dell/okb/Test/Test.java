package com.dell.okb.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.dell.okb.controller.RestCall;
import com.dell.okb.model.Request;
import com.dell.okb.util.ReadConfig;
import com.dell.okb.util.ReadExcel;

public class Test {

	private static final Logger logger = Logger.getLogger(Test.class);
	private static String instanceNo="1";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Remove the Old Categories and Add the new one in the Articles
		//removeCategories();
		
		//Remove the Categories from IM
		deleteCategories();
	}
	private static void deleteCategories() {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("C:\\OKB_Taxonomy\\log4j.properties");
		ReadConfig readConfig = new ReadConfig();
		//PropertyConfigurator.configure("/app/IntegratedSalesCat/request_jar/update/log4j.properties");
		
		BufferedReader reader;
		String categoryList= "";
		try {
				String fileName = readConfig.getPropValues("Delete_Cat_File_Name");
				reader = new BufferedReader(new FileReader(fileName));
				String line = reader.readLine();
				while (line != null) {
					categoryList=categoryList+line;
					line = reader.readLine();
			}
			reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("Error in Reading the File");
			}
	
		RestCall rc = new RestCall();
		//logger.info(categoryList);
		rc.removeCat(categoryList, instanceNo);
		
	}
	private static void removeCategories() {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("C:\\OKB_Taxonomy\\log4j.properties");
		ReadConfig readConfig = new ReadConfig();
		//PropertyConfigurator.configure("/app/IntegratedSalesCat/request_jar/update/log4j.properties");
		ReadExcel rd = new ReadExcel();
		ArrayList<Request> requestList = new ArrayList<>();
		
		try {
			//requestList = rd.readExcel("C:\\OKB_Taxonomy\\Final\\DocWithCat\\Left_Over_Docs\\Test.xlsx");
			String fileName = readConfig.getPropValues("File_Name");
			requestList = rd.readExcel(fileName);  
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RestCall rc = new RestCall();
		
		rc.removePublish(requestList,instanceNo);	
		
	}

}
