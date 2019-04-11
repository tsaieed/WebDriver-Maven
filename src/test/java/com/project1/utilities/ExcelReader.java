package com.project1.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	String xfilePath = null;
	
	public  ExcelReader(String xfilePath){
		
	this.xfilePath = xfilePath;
	
 
		try {
			fis = new FileInputStream(xfilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet =workbook.getSheetAt(0);
		row= sheet.getRow(0);
		cell=row.getCell(0);
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

	public int getRowCount(String sheetName) {
		sheet= workbook.getSheet(sheetName);
		int rowCount= sheet.getLastRowNum()+1;
		return rowCount;
	}

	public int getColumnCount(String sheetName) {
		sheet= workbook.getSheet(sheetName);
		row= sheet.getRow(0);
		int colCount = row.getLastCellNum();
		return colCount;
	}
	
	//reading cell data using columnNumber
 
	public String getCellData(String sheetName,int colNum,int rowNum)
    {
        try
        {
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);
            if(cell.getCellType() == CellType.STRING)
                return cell.getStringCellValue();
            else if(cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA)
            {
                String cellValue  = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell))
                {
                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
                    java.util.Date date = cell.getDateCellValue();
                    cellValue = df.format(date);
                }
                return cellValue;
            }else if(cell.getCellType() == CellType.BLANK)
                return " blank value";
            else
                return String.valueOf(cell.getBooleanCellValue());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "row "+rowNum+" or column "+colNum +" does not exist  in Excel";
        }
    }
	
 //reading cell data using columnName
	public String getCellData(String sheetName, String colName, int rownum) {
		  try { int colNum=-1;
		    sheet=workbook.getSheet(sheetName);
		    row=sheet.getRow(0);
		
		    for(int i =0;i<row.getLastCellNum();i++) {
			if(row.getCell(i).getStringCellValue().trim().equals(colName)) {
				colNum=i;
			}
			row=sheet.getRow(rownum);
			cell =row.getCell(colNum);
			if(cell.getCellType() == CellType.STRING)
                return cell.getStringCellValue();
            else if(cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA)
            {
                String cellValue  = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell))
                {
                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
                    java.util.Date date = cell.getDateCellValue();
                    cellValue = df.format(date);
                }
                return cellValue;
            }else if(cell.getCellType() == CellType.BLANK)
                return " blank value";
            else
                return String.valueOf(cell.getBooleanCellValue());}}
         
        catch(Exception e)
        {
            e.printStackTrace();
            return "row "+rownum+" or column "+colName +" does not exist  in Excel";
        }
		  return "no Match";
    }
       
        
		 
  
 	
//Writing cell data using columnName
public void setCellDate(String sheetName, String colName, int rowNum, String value) throws IOException{
	   sheet=workbook.getSheet(sheetName);
	   row=sheet.getRow(rowNum);
	       
	   XSSFCell cell= null;
	   int colNumber = 1;
			for(int i=0;i<row.getLastCellNum();i++) {
			if(row.getCell(i).getStringCellValue().trim().equals(colName));
			colNumber=i;}
				 
	    cell = row.getCell(colNumber);
		cell.setCellValue(value);
	    fos = new FileOutputStream(xfilePath);
		workbook.write(fos);
		fos.close(); }}

	 

