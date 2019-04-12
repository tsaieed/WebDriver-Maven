package com.project1.testCases;

 
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
 
import org.testng.annotations.Test;

import com.project1.base.TestBase;
import com.project1.utilities.ExcelReader;
import com.project1.utilities.TestUtils;

public class AddCustomerTest extends TestBase {
	
  @Test(dataProvider="dp")
	public static void addCustomerTest (String firstName, String lastName,String postCode) throws InterruptedException, IOException{ 
	   
	 click("addCustButton_xpath");
	 type("FirstName_xpath", firstName);
     Thread.sleep(2000);
     type("LastName_xpath", lastName);
     Thread.sleep(4000);
     type("PostCode_xpath", postCode);
     Thread.sleep(4000);
     click("AddCustomer_xpath");
     Thread.sleep(4000);
     wait.until(ExpectedConditions.alertIsPresent());
     Alert al = driver.switchTo().alert();
        String  actualval = al.getText();
        Assert.assertTrue( actualval.contains("Customer added successfully"));
        al.accept();
        driver.switchTo().defaultContent();
     
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
  	 
    	}}


