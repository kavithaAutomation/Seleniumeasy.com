package com.qa.application.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class UtilFunctions {
	
	/**************************************
	 * Function Name: getScreenshot
	 * Author: Kavitha
	 * Created Date: 2019-07-12
	 * Purpose: Get screen shot of the test
	 * Prerequisites: apachi commons.io dependency 
	 * Change History:	driver added in argument list  
	 * 
	 **************************************/
	
	public static String getScreenshot(WebDriver driver, String imageName) {
		  
		String currentDate = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		String destination = System.getProperty("user.dir") + "/screenshots/" + imageName + currentDate + ".png";
		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (IOException e) {
			System.out.println("Failed to capture the screen "+ e.getMessage());
		}
		
		return destination;
	}
	
	

}
