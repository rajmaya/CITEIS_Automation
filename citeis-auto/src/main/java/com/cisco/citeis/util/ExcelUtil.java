package com.cisco.citeis.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

public static Iterator<Object[]> getTestData(String strWorkbookPath,String strWorksheetName){
	List<Object[]> data = new ArrayList<Object[]>();
	try{
		Sheet sheet = null;
		FileInputStream file = null;
		if(strWorkbookPath.split("\\.")[1].equalsIgnoreCase("xls")){
			file = new FileInputStream(new File(strWorkbookPath));
	    	//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);
		 
			//Get first sheet from the workbook
			sheet = workbook.getSheet(strWorksheetName);
		}else{
			file = new FileInputStream(new File(strWorkbookPath));
			//Get the workbook instance for XLS file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
		 
			//Get first sheet from the workbook
			sheet = workbook.getSheet(strWorksheetName);
		}
		//Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = sheet.rowIterator();
		Row firstRow=rowIterator.next();
		Map<String,String> columnNamesMap=getColumnNames(firstRow);
		while(rowIterator.hasNext()){
			//Iterator<Cell> cellIterator=rowIterator.next().cellIterator();
			Row row=rowIterator.next();
			Map<String,String> rowMap=new LinkedHashMap();
			for(Entry entry:columnNamesMap.entrySet()){
				String strColumnName=entry.getKey().toString();
				int inColumnIndex=getColumnIndex(firstRow,strColumnName);
				if(inColumnIndex>=0){
					String strValue="";try{strValue=row.getCell(inColumnIndex).toString();}catch(Exception e){}
					rowMap.put(strColumnName, strValue);
					/*if(!strValue.isEmpty() && strValue !=null){
						rowMap.put(strColumnName, strValue);
					}*/
				}
			}
			//if(rowMap !=null && !rowMap.isEmpty()){
			if(rowMap.containsKey("Execute")){
				if(rowMap.get("Execute").equalsIgnoreCase("Yes"))
					data.add(new Object[]{rowMap});
			}else{
				data.add(new Object[]{rowMap});
			}
			//}
		}
		file.close();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return data.iterator();
}

public static void updateTestData(String strWorkbookPath,String strWorksheetName,String strIteration,String strColName,String strValue){
	try{
		FileInputStream file = new FileInputStream(new File(strWorkbookPath));
	    HSSFWorkbook workbook = new HSSFWorkbook(file);
	    HSSFSheet sheet = workbook.getSheet(strWorksheetName);
	    
	    Iterator<Row> rowIterator = sheet.rowIterator();
	    
	    Row firstRow=rowIterator.next();
	    
	    while(rowIterator.hasNext()){
	    	Row row=rowIterator.next();
			if(row.getCell(getColumnIndex(firstRow,"Iteration")).getStringCellValue().trim().equalsIgnoreCase(strIteration)){
				row.getCell(getColumnIndex(firstRow,strColName)).setCellValue(strValue);
				break;
			}
	    }
	    
	    file.close();
	    
	    FileOutputStream outFile =new FileOutputStream(new File(strWorkbookPath));
	    workbook.write(outFile);
	    outFile.close();
	}
	catch(Exception e){
		e.printStackTrace();
	}
}

private static Map<String,String> getColumnNames(Row row){
	Map<String,String> columnNamesMap=new LinkedHashMap();
	
	Iterator<Cell> cells=row.cellIterator();
	
	while(cells.hasNext()){
		String strColumnName=cells.next().toString();
		columnNamesMap.put(strColumnName, strColumnName);
	}
	
	return columnNamesMap;
}

private static int getColumnIndex(Row row,String strColumnName){
	Map<String,String> columnNamesMap=new LinkedHashMap();
	int inColumnIndex=-1;
	
	Iterator<Cell> cells=row.cellIterator();
	
	while(cells.hasNext()){
		Cell cell=cells.next();
		if(cell.toString().equalsIgnoreCase(strColumnName)){
			inColumnIndex=cell.getColumnIndex();
			break;
		}
	}
	
	return inColumnIndex;
}
}
