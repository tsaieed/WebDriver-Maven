package com.project1.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.project1.base.TestBase;
import com.project1.utilities.TestUtils;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Log;
 

public class CustomListners extends TestBase implements ITestListener{

	public void onTestStart(ITestResult result) {
		test = reports.startTest(result.getName().toUpperCase());
		
	}

	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, result.getName().toUpperCase()+"Pass");
		reports.endTest(test);
		reports.flush();
	}

	public void onTestFailure(ITestResult result) {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtils.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		
		test.log(LogStatus.FAIL, result.getName().toUpperCase()+"Fail with Exception:"+ result.getThrowable() );
		test.log(LogStatus.FAIL,test.addScreenCapture(TestUtils.screenshotName));
		reports.endTest(test);
		reports.flush();
		
		
		
		Reporter.log( "<a target=\"_blank\" href= "+TestUtils.screenshotName+"> Screenshot </a>" );
	    
	    
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
