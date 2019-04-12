package com.project1.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.project1.utilities.ExcelReader;
import com.project1.utilities.ExtentManager;
import com.project1.utilities.TestUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	
	
 /* Webdriver --done
  * Properties ---done
  * logs--- we need log4j jar file, .log files, log4j.properties file, Logger Class
  * Extent Reports
  * DB
  * Excel
  * Mail	
  * 
  * 
  */
	
	 public static WebDriver driver;
	 public static FileInputStream fis;
	 public static Properties config = new Properties();
	 public static Properties or = new Properties();
	 public static Logger log = Logger.getLogger("devpinoyLogger");
	 public static ExcelReader excel= new ExcelReader(System.getProperty("user.dir")+"//src//test//resources//excel//Selenium_Data.xlsx");
 	 public static WebDriverWait wait;
 	 public   ExtentReports reports= ExtentManager.getInstance(); 
 	 public static ExtentTest test;
	  
	@BeforeSuite
	public static void setUp() throws Exception {
		if(driver==null)
			fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//config.properties");
		   config.load(fis);
		   fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//OR.properties");
		   or.load(fis);
		   if(config.getProperty("browser").equalsIgnoreCase("firefox")) {
			  System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+" //src//test//resources//Driver//geckodriver.exe");
			   driver = new FirefoxDriver();
			   log.debug("browsing using irefox browser");
		   }
		   else if(config.getProperty("browser").equalsIgnoreCase("chrome")) {
			   System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir") + "//src//test//resources//Driver//chromedriver.exe");
				   driver = new ChromeDriver();
				   log.debug("browsing using chrome browser");
			   }
		   else if(config.getProperty("browser").equalsIgnoreCase("IE")) {
				  System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+" //src//test//resources//Driver//IEDriverServer.exe");
				   driver = new InternetExplorerDriver();
				   log.debug("browsing using IE browser");
			   }
		   
			 driver.get(config.getProperty("testsiteurl"));
			 log.debug("Navgated to the AUT");
			 driver.manage().window().maximize();
			 driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
            wait= new WebDriverWait(driver,5);
	
	}
	
	public static   boolean isElementPresent(By by) {
		try {driver.findElement(by);
				return true;
			}
		catch(NoSuchElementException e){ 
			return false;}
	}
	
	public static void click(String locator){
		
		if(locator.endsWith("_xpath")) {
		driver.findElement(By.xpath(or.getProperty(locator))).click();
		test.log(LogStatus.INFO, "clicking on:"+ locator);}
		else if (locator.endsWith("_css"))
		{
			driver.findElement(By.cssSelector(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "clicking on:"+ locator);}
		else if (locator.endsWith("_name"))
		{
			driver.findElement(By.name(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "clicking on:"+ locator);}
		else if (locator.endsWith("_id"))
		{
			driver.findElement(By.id(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "clicking on:"+ locator);}
		else if (locator.endsWith("_class"))
		{
			driver.findElement(By.className(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "clicking on:"+ locator);}
		else if (locator.endsWith("_linkText"))
		{
			driver.findElement(By.linkText(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "clicking on:"+ locator);}
		else if (locator.endsWith("_partialLinkText"))
		{
			driver.findElement(By.partialLinkText(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "clicking on:"+ locator);}
		else if (locator.endsWith("_tagName"))
		{
			driver.findElement(By.tagName(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "clicking on:"+ locator);}
		
	}
		
    public static void type(String locator,String value){
    	
    	if(locator.endsWith("_xpath")) {
    		driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
       		 }
    		else if (locator.endsWith("_css"))
    		{
    			driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);}
    			 
    		else if (locator.endsWith("_name"))
    		{
    			driver.findElement(By.name(or.getProperty(locator))).sendKeys(value);
    			 }
    		else if (locator.endsWith("_id"))
    		{
    			driver.findElement(By.id(or.getProperty(locator))).sendKeys(value);
    			 }
    		else if (locator.endsWith("_class"))
    		{
    			driver.findElement(By.className(or.getProperty(locator))).sendKeys(value);
    			 }
    		else if (locator.endsWith("_linkText"))
    		{
    			driver.findElement(By.linkText(or.getProperty(locator))).sendKeys(value);
    			 }
    		else if (locator.endsWith("_partialLinkText"))
    		{
    			driver.findElement(By.partialLinkText(or.getProperty(locator))).sendKeys(value);
    			 }
    		else if (locator.endsWith("_tagName"))
    		{
    			driver.findElement(By.tagName(or.getProperty(locator))).sendKeys(value);
    			 }}
    	
public static void verifyEquals(String expected,String actual) throws IOException{
			
			try {
				Assert.assertEquals(actual, expected);
			}
			catch(Throwable t) {
				//ReportNG
				TestUtils.captureScreenshot();
				Reporter.log("<br>"+"Verification Failure:"+t.getMessage()+"<br>");
				Reporter.log( "<a target=\"_blank\" href= "+TestUtils.screenshotName+"> Screenshot </a>" );
				Reporter.log("<br>");
				//Extent Reports
				test.log(LogStatus.FAIL, "Verification Failed with exception:"+t.getMessage());
				test.log(LogStatus.FAIL,test.addScreenCapture(TestUtils.screenshotName));
			}
		}
public static WebElement dropdown;
public static void select(String locator, String value){
	if(locator.endsWith("_xpath")) {
		dropdown= driver.findElement(By.xpath(or.getProperty(locator)));
   		 }
		else if (locator.endsWith("_css"))
		{
			dropdown=driver.findElement(By.cssSelector(or.getProperty(locator)));}
			 
		else if (locator.endsWith("_name"))
		{
			dropdown=driver.findElement(By.name(or.getProperty(locator)));
			 }
		else if (locator.endsWith("_id"))
		{
			dropdown=driver.findElement(By.id(or.getProperty(locator)));
			 }
		else if (locator.endsWith("_class"))
		{
			dropdown=driver.findElement(By.className(or.getProperty(locator)));
			 }
		else if (locator.endsWith("_linkText"))
		{
			dropdown=driver.findElement(By.linkText(or.getProperty(locator)));
			 }
		else if (locator.endsWith("_partialLinkText"))
		{
			dropdown=driver.findElement(By.partialLinkText(or.getProperty(locator)));
			 }
		else if (locator.endsWith("_tagName"))
		{
			dropdown=driver.findElement(By.tagName(or.getProperty(locator)));
			 }

	Select select = new Select(dropdown);
	select.selectByVisibleText(value);
}
 
	
	
 
	
	
	
@AfterSuite
	public static void tearDown() {
		if(driver!=null) {
		driver.quit();}
		log.debug("test execution completed");
		log.debug("new log");
		Reporter.log("Done");
	}
	

}
