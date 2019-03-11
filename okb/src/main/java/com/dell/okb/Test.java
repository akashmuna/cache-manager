package com.dell.okb;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadExcel rd = new ReadExcel();
		try {
			rd.readExcel("C:\\OKB_Taxonomy\\Final\\DocWithCat\\Left_Over_Docs\\Docs_Impacted.xls");
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

	}

}
