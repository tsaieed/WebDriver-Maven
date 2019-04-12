package com.project1.rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
	
 public static void main(String[] args) throws IOException {
	 
	 Properties config = new Properties();
	 FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
	 config.load(fis);
	 config.getProperty("browser");
	 System.out.println(config.getProperty("browser")); 
	 
	 Properties or = new Properties();
	 FileInputStream fis1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
 
	 or.load(fis1);
	 System.out.println(or.getProperty("bmlButton"));
}
}
