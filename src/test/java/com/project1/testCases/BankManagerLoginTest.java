package com.project1.testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.project1.base.TestBase;

 

public class BankManagerLoginTest extends TestBase {
	
	@Test
	public static   void loginAsBankManager() throws InterruptedException{
		
		log.debug("logging in as manager ");
		click("bmlButton_xpath");	 
	    Thread.sleep(5000);
	   
	    log.debug("logging in as manager successful");
	    
	    Assert.assertTrue(isElementPresent(By.xpath(or.getProperty("addCustButton_xpath"))), "Login attempt failed");
	  Reporter.log("Test run completed");
	  
	  
	}



}
