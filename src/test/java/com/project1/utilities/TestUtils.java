package com.project1.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.project1.base.TestBase;


public class TestUtils extends TestBase {
	public static String screenshotPath ;
	public static String screenshotName ;
	
	public static void captureScreenshot() throws IOException {
		Date dt= new Date();
		screenshotName = dt.toString().replace(":", "_").replace(" ", "_")+".jpg";
		screenshotPath= System.getProperty("user.dir")+"//test-output//html//"+screenshotName;
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(screenshotPath));
	}
	
@DataProvider(name = "dp")
public static Object[][] getData(Method m){
	  
	  String sheetName = m.getName();
	  int rows = excel.getRowCount(sheetName);
	  int columns = excel.getColumnCount(sheetName);
	  
	  Object[][] data = new Object[rows-1][columns];
	  
	  for(int rowNum=1;rowNum<rows;rowNum++) {
		  
		  for(int colNum=0;colNum<columns;colNum++) {
			  
			  data[rowNum-1][colNum]= excel.getCellData(sheetName, colNum, rowNum);
		  }
	  }
			  return data;
	 
  	}
 
 
	 
 

}
