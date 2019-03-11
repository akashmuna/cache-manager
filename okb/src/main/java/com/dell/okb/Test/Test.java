package com.dell.okb.Test;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.dell.okb.controller.RestCall;
import com.dell.okb.model.Request;
import com.dell.okb.util.ReadExcel;

public class Test {

	private static final Logger logger = Logger.getLogger(Test.class);
	private static String instanceNo="2";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//PropertyConfigurator.configure("C:\\OKB_Taxonomy\\log4j.properties");
		PropertyConfigurator.configure("/app/IntegratedSaleCat/log4j.properties");
		ReadExcel rd = new ReadExcel();
		ArrayList<Request> requestList = new ArrayList<>();
		String fileName = args[0];
		try {
			//requestList = rd.readExcel("C:\\OKB_Taxonomy\\Final\\DocWithCat\\Left_Over_Docs\\Test.xlsx");
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
		
		if (args[1]!= null)
			instanceNo=args[1];
		rc.removePublish(requestList,instanceNo);

	}

}
