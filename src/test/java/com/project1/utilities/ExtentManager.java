package com.project1.utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	public static ExtentReports extent;
	
	public static ExtentReports getInstance(){
		
		extent = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\extentReports\\extentReport.html", true, DisplayOrder.OLDEST_FIRST);
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\extentConfig\\extentConfig.xml"));
		
		return extent;
		
		
	}

}
