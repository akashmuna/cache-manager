package com.dell.okb.util;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import com.dell.okb.model.Request;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ReadExcel {
	
	private static final Logger logger = Logger.getLogger(ReadExcel.class);
	
	public ArrayList<Request> readExcel(String FileName) throws IOException, EncryptedDocumentException, InvalidFormatException
	{

		ArrayList<Request> RequestList = new ArrayList<>();
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(FileName));

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workbook (Multiple ways)
           =============================================================
        */

        // 1. You can obtain a sheetIterator and iterate over it
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            System.out.println("=> " + sheet.getSheetName());
        }

        // 2. Or you can use a for-each loop
        System.out.println("Retrieving Sheets using for-each loop");
        for(Sheet sheet: workbook) {
            System.out.println("=> " + sheet.getSheetName());
        }

        // 3. Or you can use a Java 8 forEach with lambda
        System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
        workbook.forEach(sheet -> {
            System.out.println("=> " + sheet.getSheetName());
        });

        /*
           ==================================================================
           Iterating over all the rows and columns in a Sheet (Multiple ways)
           ==================================================================
        */

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Now let's iterate over the columns of the current row
            Iterator<Cell> cellIterator = row.cellIterator();
           
            Request req = new Request();
            while (cellIterator.hasNext()) {
            	
                Cell cell = cellIterator.next();
                String cellValue = dataFormatter.formatCellValue(cell);
                if (cell.getColumnIndex()==0)
                	req.setDocid(cellValue);
                if (cell.getColumnIndex()==1)
                	req.setLocaleid(cellValue);
                if (cell.getColumnIndex()==2)
                	req.setRemRefCat(cellValue);
                if (cell.getColumnIndex()==3)
                	req.setNewRefCat(cellValue);
              //  System.out.print(cell.getColumnIndex()+":"+cellValue + "\t");
            }
            RequestList.add(req);
           /* System.out.println("docid:"+req.getDocid());
            System.out.println("locale:"+req.getLocaleid());
            System.out.println("remCatRef:"+req.getRemRefCat());
            System.out.println("newCatRefKey:"+req.getNewRefCat());*/
        }


        // Closing the workbook
        workbook.close();
		return RequestList;
		
	}

}
