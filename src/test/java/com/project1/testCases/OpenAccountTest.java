package com.project1.testCases;

import java.lang.reflect.Method;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project1.base.TestBase;
import com.project1.utilities.TestUtils;

import junit.framework.Assert;

public  class OpenAccountTest extends TestBase  {
	
	 @Test(dataProvider="dp")
	public static void openAccountTest (String customer, String currency) throws InterruptedException{ 

	 Thread.sleep(3000);
	 click("openAccountButton_xpath");
	 
     select("customer_id", customer);
 
     select("currency_id", currency);
   
     click("processButton_xpath");
     wait.until(ExpectedConditions.alertIsPresent());
     Alert al=driver.switchTo().alert();
     al.accept();}
     
   
     

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
	  	 
	    	}}

	  
 
